package org.jfm.controller.rest.dto;

import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.jfm.domain.entities.enums.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemPedidoDto {
	@Schema(name = "nome", example = "1", required = true)
	public String nome;
	@Schema(name = "categoria", example = "1", required = true)
	public Categoria categoria;
	@Schema(name = "preco", example = "1", required = true)
	public int preco;
	@Schema(name = "quantidade", example = "1", required = true)
	public int quantidade;

	public ItemPedidoDto() {}

	public ItemPedidoDto(
		String nome,
		Categoria categoria,
		int preco,
		int quantidade) {
		this.nome = nome;
		this.categoria = categoria;
		this.preco = preco;
		this.quantidade = quantidade;
	}

}
