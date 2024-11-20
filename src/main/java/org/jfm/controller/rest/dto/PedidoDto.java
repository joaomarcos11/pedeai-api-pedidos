package org.jfm.controller.rest.dto;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoDto {
  public UUID id;
  @Schema(name = "idCliente", example = "1", required = true)
  public UUID idCliente;
  @Schema(name = "status", example = "1", required = true)
  public Status status;
  @Schema(name = "dataCriacao", example = "2024-05-27 20:48:15.064807+00", required = true)
  public Instant dataCriacao;
  @Schema(name = "itens", example = "[{\"nome\": \"SANDUICHEICHE\", \"categoria\": \"0\", \"preco\": \"650\", \"quantidade\": 2}]", required = true)
  public List<ItemPedidoDto> itens;

  public PedidoDto() {}
  
  public PedidoDto(
    UUID id,
    UUID idCliente,
    Status status,
    Instant dataCriacao,
    List<ItemPedidoDto> itens
  ) {
    this.id = id;
    this.idCliente = idCliente;
    this.status = status;
    this.dataCriacao = dataCriacao;
    this.itens = itens;
  }

}
