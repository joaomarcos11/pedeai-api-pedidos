package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoAddRemoveItemDto {
	@Schema(name = "id", example = "1", required = true)
	public UUID id;
	@Schema(name = "idItem", example = "1", required = true)
	public UUID idItem;

	public PedidoAddRemoveItemDto() {}

	public PedidoAddRemoveItemDto(
		UUID id,
		UUID idItem
	) {
		this.id = id;
		this.idItem = idItem;
	}

}
