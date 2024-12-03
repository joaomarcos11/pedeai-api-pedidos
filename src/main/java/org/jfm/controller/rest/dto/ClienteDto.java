package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDto {

  @Schema(name = "id", example = "63a59178-39f8-4a28-a2c7-989a57ca7b54", required = true) 
  public UUID id;
  @Schema(name = "nome", example = "Gabriel Medina", required = true) 
  public String nome;
  @Schema(name = "cpf", example = "98798798798", required = true) 
  public String cpf;
  @Schema(name = "email", example = "gabrielmedina@surf.com", required = true) 
  public String email;
  @Schema(name = "active", example = "true", required = true) 
  public boolean active;

  public ClienteDto() {}
  
  public ClienteDto(
    UUID id,
    String nome,
    String cpf,
    String email,
    boolean active
  ) {
    this.id = id;
    this.nome = nome;
    this.cpf = cpf;
    this.email = email;
    this.active = active;
  }
  
}
