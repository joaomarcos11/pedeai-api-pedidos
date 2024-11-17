package org.jfm.domain.valueobjects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.jfm.factory.ItemPedidoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ItemPedidoTest {

  @Test
  public void testGetters() {
    ItemPedido itemPedido = ItemPedidoFactory.montar();

    assertNotNull(itemPedido.getIdItem());
    assertNotNull(itemPedido.getIdPedido());
  }

  @Test
  public void testSetters() {
    ItemPedido itemPedido = new ItemPedido(UUID.randomUUID(), UUID.randomUUID());
    itemPedido.setIdPedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"));
    itemPedido.setIdItem(UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"));

    ItemPedido itemPedidoMontado = ItemPedidoFactory.montar();

    assertEquals(itemPedido.getIdPedido(), itemPedidoMontado.getIdPedido());
    assertEquals(itemPedido.getIdItem(), itemPedido.getIdItem());
  
  }

  @Test
  public void testEqualsHashCode() {
    ItemPedido itemPedido = new ItemPedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"));
    ItemPedido itemPedidoMontado = ItemPedidoFactory.montar();

    assertTrue(itemPedido.equals(itemPedidoMontado));
    assertTrue(itemPedido.equals(itemPedido));
    assertFalse(itemPedido.equals(null));
    assertNotNull(itemPedido.hashCode());
  }
}
