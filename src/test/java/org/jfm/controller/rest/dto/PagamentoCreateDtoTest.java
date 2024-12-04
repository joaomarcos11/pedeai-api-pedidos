package org.jfm.controller.rest.dto;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PagamentoCreateDtoTest {
  
  @Test
  public void testGettersConstructor() {
    PagamentoCreateDto dto = new PagamentoCreateDto(UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"), 100);

    assertNotNull(dto.getPedidoID());
    assertNotNull(dto.getValue());
  }

  @Test
  public void testSetters() {
    PagamentoCreateDto dto = new PagamentoCreateDto();
    PagamentoCreateDto dto2 = new PagamentoCreateDto(UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"), 100);

    dto.setPedidoID(UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"));
    dto.setValue(100);

    assertEquals(dto.getPedidoID(), dto2.getPedidoID());
    assertEquals(dto.getValue(), dto2.getValue());
  }

}
