package org.jfm.bootloader;

// import org.jfm.controller.rest.restclient.ClienteServiceClient;
import org.jfm.domain.ports.ItemRepository;
import org.jfm.domain.ports.PedidoRepository;
import org.jfm.domain.ports.PedidoStatusRepository;
import org.jfm.domain.services.ItemService;
import org.jfm.domain.services.PedidoService;
import org.jfm.domain.usecases.ItemUseCase;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;

@ApplicationScoped
public class AppContext {
  @Produces
  public ItemService itemService(ItemRepository itemRepository) {
      return new ItemService(itemRepository);
  };

  @Produces
  public PedidoService pedidoService(PedidoRepository pedidoRepository, PedidoStatusRepository pedidoStatusRepository, ItemUseCase itemUseCase) {
  // public PedidoService pedidoService(PedidoRepository pedidoRepository, PedidoStatusRepository pedidoStatusRepository, ItemUseCase itemUseCase, ClienteServiceClient clienteServiceClient) {
      // PagamentoGateway gatewayPagamento;
      
      // if (!GATEWAY_PAGAMENTO_MOCK.equals("true")) {
      //     gatewayPagamento = new org.jfm.infra.payment.adaptermercadopago.PagamentoGatewayImpl();
      // } else {
      //     gatewayPagamento = new org.jfm.infra.payment.adaptermock.PagamentoGatewayImpl();
      // }

      return new PedidoService(pedidoRepository, pedidoStatusRepository, itemUseCase);
      // return new PedidoService(pedidoRepository, pedidoStatusRepository, itemUseCase, clienteServiceClient);
  };
}
