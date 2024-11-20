package org.jfm.factory.dto;

import java.util.UUID;

import org.jfm.controller.rest.dto.PedidoCreateDto;

public class PedidoCreateDtoFactory {
  
  public static PedidoCreateDto montar() {
    return new PedidoCreateDto(null, ItemPedidoCreateDtoFactory.montarLista());
    // return new PedidoCreateDto(UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), ItemPedidoCreateDtoFactory.montarLista());
  }
}
