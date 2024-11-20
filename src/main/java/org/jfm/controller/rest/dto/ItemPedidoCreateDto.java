package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoCreateDto {

  @Schema(name = "idItem", example = "257ae14b-8bb7-4a80-9a68-22197f72ff47", required = true)
  public UUID idItem;
  @Schema(name = "quantidade", example = "1", required = true) 
  public int quantidade;

  public ItemPedidoCreateDto() {}

  public ItemPedidoCreateDto(
    UUID idItem,
    int quantidade
  ) {
    this.idItem = idItem;
    this.quantidade = quantidade;
  }
  
}
