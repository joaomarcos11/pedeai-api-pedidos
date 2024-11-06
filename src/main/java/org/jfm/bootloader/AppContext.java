package org.jfm.bootloader;

import jakarta.enterprise.inject.Produces;

public class AppContext {
// @Produces
// public ItemService itemService(ItemRepository itemRepository) {
//     return new ItemService(itemRepository);
// };

// @Produces
// public PedidoService pedidoService(PedidoRepository pedidoRepository, PedidoStatusRepository pedidoStatusRepository, PedidoPagamentoRepository pedidoPagamentoRepository, ClienteUseCase clienteUseCase, ItemUseCase itemUseCase, Notificacao notificacao) {
//     PagamentoGateway gatewayPagamento;
    
//     if (!GATEWAY_PAGAMENTO_MOCK.equals("true")) {
//         gatewayPagamento = new org.jfm.infra.payment.adaptermercadopago.PagamentoGatewayImpl();
//     } else {
//         gatewayPagamento = new org.jfm.infra.payment.adaptermock.PagamentoGatewayImpl();
//     }

//     return new PedidoService(pedidoRepository, pedidoStatusRepository, pedidoPagamentoRepository, clienteUseCase, itemUseCase, gatewayPagamento, notificacao);
// };
}
