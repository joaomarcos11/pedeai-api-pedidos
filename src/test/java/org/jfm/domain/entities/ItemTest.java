package org.jfm.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.exceptions.Exceptions.InvalidEntityException;
import org.jfm.factory.ItemFactory;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ItemTest {
  
  @Test
  public void testGetters() {
    Item item = new Item(Categoria.LANCHE,	550,	UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"),	"SANDUICHE", "um delicioso sanduiche", "/var/app/imagens/sanduiche.jpg");
    // Item item = new Item();

    // item.setCategoria(Categoria.LANCHE);
    // item.setPreco(550);
    // item.setId(UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"));
    // item.setNome("SANDUICHE");
    // item.setDescricao("um delicioso sanduiche");
    // item.setImagem("/var/app/imagens/sanduiche.jpg");

    assertNotNull(item.getCategoria());
    assertNotNull(item.getPreco());
    assertNotNull(item.getId());
    assertNotNull(item.getNome());
    assertNotNull(item.getDescricao());
    assertNotNull(item.getImagem());
  }

  @Test
  public void testGettersConstructor() {
    Item item = new Item(UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"), "SANDUICHE", 550, Categoria.LANCHE, "um delicioso sanduiche", "/var/app/imagens/sanduiche.jpg");

    assertNotNull(item.getCategoria());
    assertNotNull(item.getPreco());
    assertNotNull(item.getId());
    assertNotNull(item.getNome());
    assertNotNull(item.getDescricao());
    assertNotNull(item.getImagem());
  }
  
  @Test
  public void testSetters() {
    Item item = new Item();

    item.setCategoria(Categoria.LANCHE);
    item.setPreco(550);
    item.setId(UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"));
    item.setNome("SANDUICHE");
    item.setDescricao("um delicioso sanduiche");
    item.setImagem("/var/app/imagens/sanduiche.jpg");

    Item itemMontado = ItemFactory.montarItem();

    assertEquals(item.getCategoria(), itemMontado.getCategoria());
    assertEquals(item.getPreco(), itemMontado.getPreco());
    assertEquals(item.getId(), itemMontado.getId());
    assertEquals(item.getNome(), itemMontado.getNome());
    assertEquals(item.getDescricao(), itemMontado.getDescricao());
    assertEquals(item.getImagem(), itemMontado.getImagem());
  }

  @Test
  public void testEqualsHashCode() {
    Item item = new Item(Categoria.LANCHE,	550,	UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47"),	"SANDUICHE", "um delicioso sanduiche", "/var/app/imagens/sanduiche.jpg");

    assertEquals(item.equals(ItemFactory.montarItem()), true);
    assertEquals(item.equals(null), false);
    assertNotNull(item.hashCode());
  }

  @Test
  public void testValidarNome() {
    Item item = ItemFactory.montarItem();
    item.setNome("");

    assertThrows(InvalidEntityException.class, () -> item.validar());
  }

  @Test
  public void testValidarPreco() {
    Item item = ItemFactory.montarItem();
    item.setPreco(-100);

    assertThrows(InvalidEntityException.class, () -> item.validar());
  }

}
