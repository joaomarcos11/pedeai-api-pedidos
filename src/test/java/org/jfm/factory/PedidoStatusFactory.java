package org.jfm.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;

public class PedidoStatusFactory {
  
  public static PedidoStatus montar() {
    PedidoStatus pedidoStatus = new PedidoStatus(UUID.fromString("58fba074-519b-470a-91c2-8b26d2b08f32"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.AGUARDANDO_PAGAMENTO, Status.PAGO);
    return pedidoStatus;
  }

  public static List<PedidoStatus> montarLista() {
    List<PedidoStatus> listaPedidoStatus = new ArrayList<PedidoStatus>(List.of(
      new PedidoStatus(UUID.fromString("58fba074-519b-470a-91c2-8b26d2b08f32"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.AGUARDANDO_PAGAMENTO, Status.PAGO),
      new PedidoStatus(UUID.fromString("c0a0a1c1-987d-4c95-93df-a535eabbb96c"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.DISPONIVEL, Status.CONCLUIDO)
    ));

    return listaPedidoStatus;
  }



}
