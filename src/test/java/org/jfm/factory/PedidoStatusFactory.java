package org.jfm.factory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;

public class PedidoStatusFactory {
  
  public static PedidoStatus montar() {
    PedidoStatus pedidoStatus = new PedidoStatus(UUID.fromString("58fba074-519b-470a-91c2-8b26d2b08f32"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.AGUARDANDO_PAGAMENTO, Status.PAGO);
    return pedidoStatus;
  }

}
