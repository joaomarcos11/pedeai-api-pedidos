package org.jfm.domain.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.Instant;
import java.util.UUID;
import java.util.Map;

import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.Exceptions.InvalidEntityException;
import org.jfm.factory.ItemFactory;
import org.jfm.factory.PedidoFactory;
import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PedidoTest {

  private final Instant dataCriacaoMockada = Instant.parse("2024-11-15T20:23:20Z");
  
  @Test
  public void testGetters() {
    Pedido pedido = new Pedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"), Status.AGUARDANDO_PAGAMENTO, Instant.now());

    assertNotNull(pedido.getId());
    assertNotNull(pedido.getIdCliente());
    assertNotNull(pedido.getStatus());
    assertNotNull(pedido.getDataCriacao());

    Pedido pedido2 = new Pedido(UUID.fromString("7f7e383e-58e2-43b6-a18a-f507003b45f8"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.CONCLUIDO);
    assertNotNull(pedido2.getId());
    assertNotNull(pedido2.getIdCliente());
    assertNotNull(pedido2.getStatus());
  }

  @Test
  public void testSetters() {
    Pedido pedido = new Pedido();

    pedido.setId(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"));
    pedido.setIdCliente(UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"));
    pedido.setStatus(Status.AGUARDANDO_PAGAMENTO);
    pedido.setDataCriacao(dataCriacaoMockada);

    Pedido pedidoMontado = PedidoFactory.montar();
    pedidoMontado.setDataCriacao(dataCriacaoMockada);

    assertEquals(pedido.getId(), pedidoMontado.getId());
    assertEquals(pedido.getIdCliente(), pedidoMontado.getIdCliente());
    assertEquals(pedido.getStatus(), pedidoMontado.getStatus());
    assertEquals(pedido.getDataCriacao(), pedidoMontado.getDataCriacao());
  
  }

  @Test
  public void testEqualsHashCode() {
    Pedido pedido = new Pedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"), Status.AGUARDANDO_PAGAMENTO, Instant.now());
    pedido.setDataCriacao(dataCriacaoMockada);

    Pedido pedidoMontado = PedidoFactory.montar();
    pedidoMontado.setDataCriacao(dataCriacaoMockada);

    assertEquals(pedido.equals(PedidoFactory.montar()), true);
    assertEquals(pedido.equals(pedido), true);
    assertEquals(pedido.equals(null), false);
    assertNotNull(pedido.hashCode());
  }

  @Test
  public void testValidar() {
    Pedido pedido = PedidoFactory.montar();
    pedido.setItens(null);

    assertThrows(InvalidEntityException.class, () -> pedido.validar());
  }

}

