package org.jfm.factory;

import java.util.Set;
import java.util.UUID;

import org.jfm.domain.valueobjects.ItemPedido;
import org.jfm.infra.repository.adaptersql.entities.ItemEntity;
import org.jfm.infra.repository.adaptersql.entities.ItemPedidoEntity;
import org.jfm.infra.repository.adaptersql.entities.ItemPedidoKey;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;

public class ItemPedidoFactory {
  public static ItemPedido montar()   {
    return new ItemPedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"));
  }

  public static Set<ItemPedidoEntity> montarItensPedido() {
    PedidoEntity pedidoEntity = new PedidoEntity();
    pedidoEntity.setId(UUID.fromString("7f7e383e-58e2-43b6-a18a-f507003b45f8"));

    ItemEntity itemEntity = new ItemEntity();
    itemEntity.setId(UUID.fromString("9b5b286e-e617-4f20-80ad-1824c97ec71b"));
   
    ItemPedidoKey itemPedidoKey = new ItemPedidoKey();
    itemPedidoKey.setPedidoId(UUID.fromString("7f7e383e-58e2-43b6-a18a-f507003b45f8"));
    itemPedidoKey.setItemId(UUID.fromString("9b5b286e-e617-4f20-80ad-1824c97ec71b"));

    ItemPedidoEntity itemPedidoEntity = new ItemPedidoEntity();

    itemPedidoEntity.setId(itemPedidoKey);
    itemPedidoEntity.setPedido(pedidoEntity);
    itemPedidoEntity.setItem(itemEntity);
    itemPedidoEntity.setQuantidade(1);

    Set<ItemPedidoEntity> itemPedidoSet = Set.of(itemPedidoEntity);

    return itemPedidoSet;
  }
}
