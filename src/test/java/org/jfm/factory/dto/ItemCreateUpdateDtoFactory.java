package org.jfm.factory.dto;

import org.jfm.controller.rest.dto.ItemCreateUpdateDto;
import org.jfm.domain.entities.enums.Categoria;

public class ItemCreateUpdateDtoFactory {
  
  public static ItemCreateUpdateDto montar() {
    return new ItemCreateUpdateDto("nome", 10, Categoria.LANCHE, "descricao", "imagem");
  }
}
