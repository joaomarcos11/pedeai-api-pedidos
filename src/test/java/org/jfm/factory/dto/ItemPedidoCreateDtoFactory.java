package org.jfm.factory.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jfm.controller.rest.dto.ItemPedidoCreateDto;

public class ItemPedidoCreateDtoFactory {
  public static List<ItemPedidoCreateDto> montarLista() {
    List<ItemPedidoCreateDto> lista = new ArrayList<ItemPedidoCreateDto>(List.of(
      new ItemPedidoCreateDto(UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"), 1),
      new ItemPedidoCreateDto(UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"), 2)
    ));

    return lista;
  }
}
