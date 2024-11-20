package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

  @Schema(name = "nome", example = "Gabriel Medina", required = true) 
  public String nome;
  @Schema(name = "cpf", example = "98798798798", required = true) 
  public String cpf;
  @Schema(name = "email", example = "gabrielmedina@surf.com", required = true) 
  public String email;
  @Schema(name = "ativo", example = "true", required = true) 
  public boolean ativo;

  public ClienteDto() {}
  
  public ClienteDto(
    String nome,
    String cpf,
    String email,
    boolean ativo
  ) {
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.ativo = ativo;
  }
  
}
