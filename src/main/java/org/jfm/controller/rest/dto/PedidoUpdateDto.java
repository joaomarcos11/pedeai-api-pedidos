package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoUpdateDto {
  @Schema(name = "status", example = "1", required = true)
  public Status status;

  public PedidoUpdateDto() {}

  public PedidoUpdateDto(Status status) {
    this.status = status;
  }
        
}
