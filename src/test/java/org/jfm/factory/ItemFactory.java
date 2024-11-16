package org.jfm.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;

public class ItemFactory {
  
  public static List<Item> montarListaItens() {
    List<Item> itens = new ArrayList<Item>(List.of(
      new Item(Categoria.LANCHE,	550,	UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"),	"SANDUICHE", "um delicioso sanduiche", "/var/app/imagens/sanduiche.jpg"),
      new Item(Categoria.BEBIDA,	350,	UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"),	"REFRIGERANTE", "um delicioso refrigerante", "/var/app/imagens/refrigerante.jpg"),
      new Item(Categoria.ACOMPANHAMENTO,	675,	UUID.fromString("f66d8bf8-f350-46ba-8893-902bfd3e556e"),	"BATATA FRITA", "batata-frita salgada", "/var/app/imagens/batata-frita.jpg"),
      new Item(Categoria.SOBREMESA,	460,	UUID.fromString("9b5b286e-e617-4f20-80ad-1824c97ec71b"),	"SORVETE", "uma deliciosa sobremesa", "/var/app/imagens/sorvete.jpg"),
      new Item(Categoria.LANCHE,	450,	UUID.fromString("6907dc62-e579-4178-ba30-3d7e4cea021d"),	"X-VEGETARIANO", "um delicioso lanche vegetariano", "/var/app/imagens/x-vegetariano.jpg"),
      new Item(Categoria.BEBIDA,	350,	UUID.fromString("dd494312-7c6c-40c0-8449-0574c715325d"),	"SUCO DE LARANJA", "um suco feito da fruta fresca", "/var/app/imagens/suco-de-laranja.jpg"),
      new Item(Categoria.ACOMPANHAMENTO,	650,	UUID.fromString("4e1bb65c-b3c0-4229-964b-c10241b7aca4"),	"DADINHO DE TAPIOCA", "um lanche saboroso", "/var/app/imagens/dadinho-de-tapioca.jpg"),
      new Item(Categoria.SOBREMESA,	710,	UUID.fromString("082db643-11a4-4bf8-8115-72148e24261d"),	"PUDIM", "uma sobremesa deliciosa", "/var/app/imagens/pudim.jpg")
    ));

    return itens;
  }

  public static List<Item> montarListaItensCategoria() {
    List<Item> itens = new ArrayList<Item>(List.of(
      new Item(Categoria.BEBIDA,	350,	UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"),	"REFRIGERANTE", "um delicioso refrigerante", "/var/app/imagens/refrigerante.jpg"),
      new Item(Categoria.BEBIDA,	350,	UUID.fromString("dd494312-7c6c-40c0-8449-0574c715325d"),	"SUCO DE LARANJA", "um suco feito da fruta fresca", "/var/app/imagens/suco-de-laranja.jpg")
    ));

    return itens;
  }

  public static Item montarItem() {
    Item item = new Item(Categoria.LANCHE,	550,	UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"),	"SANDUICHE", "um delicioso sanduiche", "/var/app/imagens/sanduiche.jpg");
    return item;
  }

}
