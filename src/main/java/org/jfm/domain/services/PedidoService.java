package org.jfm.domain.services;

import java.time.Instant;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jfm.controller.rest.client.ClienteService;
import org.jfm.controller.rest.client.PagamentoService;
import org.jfm.controller.rest.dto.ClienteDto;
import org.jfm.controller.rest.dto.PagamentoCreateDto;
import org.jfm.controller.rest.dto.PagamentoDto;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.ClientException;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.ports.PedidoStatusRepository;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.domain.usecases.PedidoUseCase;
import jakarta.inject.Inject;

public class PedidoService implements PedidoUseCase {

    PedidoRepository pedidoRepository;

    PedidoPagamentoRepository pedidoPagamentoRepository;

    PedidoStatusRepository pedidoStatusRepository;

    ItemUseCase itemUseCase;

    @Inject
    @RestClient
    ClienteService clienteService;

    @Inject
    @RestClient
    PagamentoService pagamentoService;

    private static final Set<Status> STATUS_EM_ANDAMENTO = EnumSet.of(Status.PAGO, Status.PREPARANDO,
            Status.DISPONIVEL);
    private static final String PEDIDO_APROVADO = "approved";

    public PedidoService(
        PedidoRepository pedidoRepository, 
        PedidoStatusRepository pedidoStatusRepository,
        ItemUseCase itemUseCase) {
        this.pedidoRepository = pedidoRepository;
        this.pedidoStatusRepository = pedidoStatusRepository;
        this.itemUseCase = itemUseCase;
    }

    @Override
    public Pedido criar(Pedido pedido) {
        pedido.validar();

        if (pedido.getIdCliente() != null) {
            // PagamentoDto response = clienteService.criar(new PagamentoCreateDto(pedido.getId(), 100));
            // ClienteDto response = clienteService.buscarPorId(pedido.getIdCliente());
            // RestResponse<ClienteDto> response = clienteService.buscarPorId(pedido.getIdCliente());
            try {
                ClienteDto clienteDto = clienteService.buscarPorId(pedido.getIdCliente());
                if (clienteDto == null) {
                    throw new EntityNotFoundException(ErrosSistemaEnum.CLIENTE_NOT_FOUND.getMessage());
                }
            } catch(Exception e) {
                System.out.println("Exception cliente: " + e.getMessage());
                System.out.println(ErrosSistemaEnum.FALHA_COMUNICACAO.getMessage());
                // throw new ClientException(ErrosSistemaEnum.FALHA_COMUNICACAO.getMessage());
            }
        }

        List<Item> itens = itemUseCase.listar();
        
        UUID itemUuid = UUID.fromString("6907dc62-e579-4178-ba30-3d7e4cea021d");
        Item itemEscolhido = new Item();

        for (Item item : itens) {
            if (item.getId().equals(itemUuid)) {
                itemEscolhido = item;
            }
        }

        Map<Item,Integer> mapItens = new HashMap<>();
        mapItens.put(itemEscolhido, 2);

        pedido.setItens(mapItens);

        UUID id = UUID.randomUUID();
        pedido.setId(id);
        pedido.setDataCriacao(Instant.now());
        pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);

        UUID pedidoId = pedidoRepository.criar(pedido);
        pedidoStatusRepository.criar(new PedidoStatus(UUID.randomUUID(), id, null, pedido.getStatus()));

        boolean pagamentoAprovado = criarPagamento(pedido);

        if (pagamentoAprovado) {
            pedido.setStatus(Status.PAGO);
        } else {
            pedido.setStatus(Status.CANCELADO);
        }

        PedidoStatus pedidoStatus = new PedidoStatus(UUID.randomUUID(), pedido.getId(), Status.AGUARDANDO_PAGAMENTO, pedido.getStatus());

        pedidoRepository.editar(pedido);
        pedidoStatusRepository.criar(pedidoStatus);

        return pedido;
    };

    @Override
    public List<Pedido> listar() {
        List<Pedido> pedidos = pedidoRepository.listar();

        Collections.sort(pedidos, new Comparator<Pedido>() {
            public int compare(Pedido ped1, Pedido ped2) {
                return ped1.getDataCriacao().compareTo(ped2.getDataCriacao());
            }
        });

        return pedidos;
    };

    @Override
    public List<Pedido> listarEmAndamento() {
        List<Pedido> pedidos = pedidoRepository.listar();

        pedidos = pedidos.stream()
                .filter(pedido -> STATUS_EM_ANDAMENTO.contains(pedido.getStatus()))
                .sorted(Comparator.comparing(Pedido::getStatus, Comparator.comparingInt(this::getStatusPrioridade))
                        .thenComparing(Pedido::getDataCriacao))
                .collect(Collectors.toList());

        return pedidos;
    }

    @Override
    public Pedido buscarPorId(UUID id) {
        return pedidoRepository.buscarPorId(id);
    };

    @Override
    public List<Pedido> listarPorStatus(Status status) {
        List<Pedido> pedidos = pedidoRepository.listarPorStatus(status);

        Collections.sort(pedidos, new Comparator<Pedido>() {
            public int compare(Pedido ped1, Pedido ped2) {
                return ped1.getDataCriacao().compareTo(ped2.getDataCriacao());
            }
        });

        return pedidos;
    }

    @Override
    public void editar(Pedido pedido) {
        Pedido pedidoEditar = pedidoRepository.buscarPorId(pedido.getId());
        PedidoStatus pedidoStatus = new PedidoStatus(UUID.randomUUID(), pedidoEditar.getId(), pedidoEditar.getStatus(),
                pedido.getStatus());

        pedidoEditar.setStatus(pedido.getStatus());

        pedidoRepository.editar(pedidoEditar);
        pedidoStatusRepository.criar(pedidoStatus);
    };

    @Override
    public List<PedidoStatus> buscarHistoricoStatus(UUID id) {
        List<PedidoStatus> pedidosStatus = pedidoStatusRepository.listarPorPedidoId(id);

        Collections.sort(pedidosStatus, new Comparator<PedidoStatus>() {
            public int compare(PedidoStatus ped1, PedidoStatus ped2) {
                return ped2.getDataCriacao().compareTo(ped1.getDataCriacao());
            }
        });

        return pedidosStatus;
    }

    @Override
    public boolean estaPago(UUID id) {
        Pedido pedido = pedidoRepository.buscarPorId(id);

        return pedido.getStatus() == Status.PAGO;
    }

    public boolean criarPagamento(Pedido pedido) {
        int valorFinal = 0;
    
        for (Item item : pedido.getItens().keySet()) {
            valorFinal += pedido.getItens().get(item);
        }

        PagamentoCreateDto pagamento = new PagamentoCreateDto(pedido.getId(), valorFinal);
        try {
            PagamentoDto pagamentoDto = pagamentoService.criar(pagamento);
            return pagamentoDto.getStatus().equals(PEDIDO_APROVADO);
        } catch(Exception e) {
            return false;
        }
    }

    public int getStatusPrioridade(Status status) {
        switch (status) {
            case DISPONIVEL:
                return 1;
            case PREPARANDO:
                return 2;
            case PAGO:
                return 3;
            default:
                return 4;
        }
    }
}
