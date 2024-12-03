package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PagamentoDto {

  @Schema(name = "dateCreated", example = "", required = true) 
  public String dateCreated;
  @Schema(name = "dateApproved", example = "", required = true) 
  public String dateApproved;
  @Schema(name = "id", example = "674ce266ebd2f545df75004d", required = true) 
  public UUID id;
  @Schema(name = "status", example = "true", required = true) 
  public String status;

  public PagamentoDto() {}
}
