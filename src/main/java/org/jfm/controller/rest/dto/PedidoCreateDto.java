package org.jfm.controller.rest.dto;

import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoCreateDto {
  @Schema(name = "idCliente", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54", required = true)
  public UUID idCliente;
  @Schema(name = "itensPedidos", example = "[{\"id\": \"6907dc62-e579-4178-ba30-3d7e4cea021d\", \"quantidade\": 2}]", required = true)
  List<ItemPedidoCreateDto> itensPedidos;

  public PedidoCreateDto() {}

  public PedidoCreateDto(
    UUID idCliente,
    List<ItemPedidoCreateDto> itensPedidos
  ) {
    this.idCliente = idCliente;
    this.itensPedidos = itensPedidos;
  }
}
