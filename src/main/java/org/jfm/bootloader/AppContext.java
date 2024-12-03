package org.jfm.bootloader;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jfm.controller.rest.client.ClienteService;
import org.jfm.controller.rest.client.PagamentoService;
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
  public PedidoService pedidoService(
    PedidoRepository pedidoRepository, 
    PedidoStatusRepository pedidoStatusRepository, 
    ItemUseCase itemUseCase,
    @RestClient ClienteService clienteService,
    @RestClient PagamentoService pagamentoService
  ) {
    return new PedidoService(
      pedidoRepository, 
      pedidoStatusRepository, 
      itemUseCase,
      clienteService,
      pagamentoService
    );
  };
}
