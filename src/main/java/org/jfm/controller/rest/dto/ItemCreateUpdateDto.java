package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCreateUpdateDto {
  @Schema(name = "nome", example = "1", required = true)
  public String nome;
  @Schema(name = "preco", example = "1", required = true)
  public int preco;
  @Schema(name = "categoria", example = "1", required = true)
  public Categoria categoria;
  @Schema(name = "descricao", example = "1", required = true)
  public String descricao;
  @Schema(name = "imagem", example = "1", required = true)
  public String imagem;

  public ItemCreateUpdateDto() {}

  public ItemCreateUpdateDto(
    String nome,
    int preco,
    Categoria categoria,
    String descricao,
    String imagem
  ) {
    this.nome = nome;
    this.preco = preco;
    this.categoria = categoria;
    this.descricao = descricao;
    this.imagem = imagem;
  }

}
