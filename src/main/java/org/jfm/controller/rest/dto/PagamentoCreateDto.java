package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoCreateDto {

  @Schema(name = "pedidoId", example = "c215b5a1-9421-4cfd-982a-00f64f470252", required = true) 
  public UUID pedidoId;
  @Schema(name = "value", example = "100.5", required = true) 
  public int value;

  public PagamentoCreateDto() {}
  
  public PagamentoCreateDto(
    UUID pedidoId,
    int value
  ) {
    this.pedidoId = pedidoId;
    this.value = value;
  }
}
