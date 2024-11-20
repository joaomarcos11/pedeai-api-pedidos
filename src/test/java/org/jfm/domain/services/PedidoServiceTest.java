package org.jfm.domain.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.ports.PedidoPagamentoRepository;
import org.jfm.domain.ports.PedidoStatusRepository;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.factory.PedidoFactory;
import org.jfm.factory.PedidoStatusFactory;
import org.jfm.infra.repository.adaptersql.PedidoRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class PedidoServiceTest {
  
  @Inject
  PedidoService service;
  
  @InjectMocks
  PedidoService serviceMock;

  @Mock
  PedidoRepositoryImpl repository;

  @Mock
  PedidoPagamentoRepository pedidoPagamentoRepository;
  
  @Mock
  PedidoStatusRepository pedidoStatusRepository;
  
  @Mock
  ItemUseCase itemUseCase;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriarAnonimo() {
    Pedido pedido = PedidoFactory.montar();
    pedido.setId(UUID.randomUUID());
    pedido.setIdCliente(null);
    pedido.setItens(PedidoFactory.montarItensPedidos());

    serviceMock.criar(pedido);

    verify(repository, times(1)).criar(pedido);
  }

  @Test
  public void testListar() {
    List<Pedido> pedidos = PedidoFactory.montarListaOrdenada();
    
    Assertions.assertEquals(service.listar(), pedidos);
  }

  @Test
  public void testListarEmAndamento() {
    List<Pedido> pedidos = PedidoFactory.montarListaEmAndamento();
    
    Assertions.assertEquals(service.listarEmAndamento(), pedidos);
  }

  @Test
  public void testBuscarPorId() {
    Pedido pedido = PedidoFactory.montar();

    Assertions.assertEquals(service.buscarPorId(pedido.getId()), pedido);
  }
  
  @Test
  public void testListarPorStatus() {
    List<Pedido> pedidos = PedidoFactory.montarListaAguardaPgto();
    
    Assertions.assertEquals(service.listarPorStatus(Status.AGUARDANDO_PAGAMENTO), pedidos);
  }

  @Test
  public void testEditar() {
    Pedido pedidoEditar = PedidoFactory.montar();
    
    Pedido pedidoPago = PedidoFactory.montarPago();
    pedidoPago.setDataCriacao(pedidoEditar.getDataCriacao());
    
    when(repository.buscarPorId(pedidoEditar.getId())).thenReturn(pedidoEditar);
    // when(UUID.randomUUID()).thenReturn(UUID.fromString("58fba074-519b-470a-91c2-8b26d2b08f32"));
    
    pedidoEditar.setStatus(Status.PAGO);
    
    PedidoStatus pedidoStatus = PedidoStatusFactory.montar();
    serviceMock.editar(pedidoEditar);
    
    verify(repository, times(1)).editar(pedidoEditar);
    // verify(pedidoStatusRepository, times(1)).criar(pedidoStatus);
  }

  @Test
  public void testBuscarHistoricoStatus() {
    List<PedidoStatus> pedidoStatus = PedidoFactory.montarListaHistoricoStatus();
    Pedido pedido = PedidoFactory.montar();

    when(pedidoStatusRepository.listarPorPedidoId(pedido.getId())).thenReturn(pedidoStatus);

    serviceMock.buscarHistoricoStatus(pedido.getId());

    verify(pedidoStatusRepository, times(1)).listarPorPedidoId(pedido.getId());
  }

  @Test
  public void testEstaPago() {
    Pedido pedido = PedidoFactory.montarPago();
    Assertions.assertTrue(service.estaPago(pedido.getId()));
    
    pedido = PedidoFactory.montar();
    Assertions.assertFalse(service.estaPago(pedido.getId()));
  }

  @Test
  public void testGetStatusPrioridade() {
    Pedido pedido = PedidoFactory.montar();
    pedido.setStatus(Status.DISPONIVEL);
    Assertions.assertEquals(service.getStatusPrioridade(pedido.getStatus()), 1);
    
    pedido.setStatus(Status.PREPARANDO);
    Assertions.assertEquals(service.getStatusPrioridade(pedido.getStatus()), 2);
    
    pedido.setStatus(Status.PAGO);
    Assertions.assertEquals(service.getStatusPrioridade(pedido.getStatus()), 3);
    
    pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);
    Assertions.assertEquals(service.getStatusPrioridade(pedido.getStatus()), 4);
  }

}
