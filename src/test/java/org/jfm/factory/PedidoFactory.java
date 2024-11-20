package org.jfm.factory;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.entities.enums.Status;

public class PedidoFactory {
    private static final Set<Status> STATUS_EM_ANDAMENTO = EnumSet.of(Status.PAGO, Status.PREPARANDO, Status.DISPONIVEL);

  public static Pedido montar() {
    Pedido pedido = new Pedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"), Status.AGUARDANDO_PAGAMENTO);
    pedido.setDataCriacao(Instant.now()); // TODO: mudar aqui e no import para string constante
    
    return pedido;
  }

  public static Pedido montarPago() {
    Pedido pedido = new Pedido(UUID.fromString("34d475e0-1a95-404a-964e-9f3d95eaa90e"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.PAGO, Instant.now());
    pedido.setDataCriacao(Instant.now()); // TODO: mudar aqui e no import para string constante
    
    return pedido;
  }

  public static Pedido montarEmPeparacao() {
    Pedido pedido = new Pedido(UUID.fromString("1e9873d8-43b8-4440-a0f9-68b05032af11"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.PREPARANDO, Instant.now());
    pedido.setDataCriacao(Instant.now()); // TODO: mudar aqui e no import para string constante
    return pedido;
  }

  public static List<Pedido> montarListaOrdenada() {
    List<Pedido> pedidos = new ArrayList<Pedido>(List.of(
      new Pedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"), Status.AGUARDANDO_PAGAMENTO, Instant.now()),
      new Pedido(UUID.fromString("7f7e383e-58e2-43b6-a18a-f507003b45f8"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.CONCLUIDO, Instant.now()),
      new Pedido(UUID.fromString("1e9873d8-43b8-4440-a0f9-68b05032af11"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.PREPARANDO, Instant.now()),
      new Pedido(UUID.fromString("34d475e0-1a95-404a-964e-9f3d95eaa90e"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.PAGO, Instant.now())
    ));

    Collections.sort(pedidos, new Comparator<Pedido>() {
            public int compare(Pedido ped1, Pedido ped2) {
                return ped1.getDataCriacao().compareTo(ped2.getDataCriacao());
            }
        });
    
    return pedidos;
  }

  public static List<PedidoStatus> montarListaHistoricoStatus() {
    List<PedidoStatus> pedidosStatus = new ArrayList<PedidoStatus>(List.of(
      new PedidoStatus(UUID.fromString("19ca0888-b83a-4380-a974-167d0f76f091"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.PREPARANDO, Status.DISPONIVEL, Instant.now()),
      new PedidoStatus(UUID.fromString("c0a0a1c1-987d-4c95-93df-a535eabbb96c"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.DISPONIVEL, Status.CONCLUIDO, Instant.now())
    ));
    // List<PedidoStatus> pedidosStatus = new ArrayList<PedidoStatus>(List.of(
    //   new PedidoStatus(UUID.fromString("19ca0888-b83a-4380-a974-167d0f76f091"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.PREPARANDO, Status.DISPONIVEL, PedidoFactory.getDataCriacaoMock("2024-11-10 20:23:20")),
    //   new PedidoStatus(UUID.fromString("c0a0a1c1-987d-4c95-93df-a535eabbb96c"), UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), Status.DISPONIVEL, Status.CONCLUIDO, PedidoFactory.getDataCriacaoMock("2024-11-10 20:25:20"))
    // ));

    Collections.sort(pedidosStatus, new Comparator<PedidoStatus>() {
        public int compare(PedidoStatus ped1, PedidoStatus ped2) {
            return ped2.getDataCriacao().compareTo(ped1.getDataCriacao());
        }
    });
    
    return pedidosStatus;
  }


  public static List<Pedido> montarListaEmAndamento() {
    List<Pedido> pedidos = new ArrayList<Pedido>(List.of(
      new Pedido(UUID.fromString("1e9873d8-43b8-4440-a0f9-68b05032af11"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.PREPARANDO, Instant.now()),
      new Pedido(UUID.fromString("34d475e0-1a95-404a-964e-9f3d95eaa90e"), UUID.fromString("e7fe16ff-38f9-43db-bee9-e535f04d4272"), Status.PAGO, Instant.now())
    ));

    return pedidos;
  }

  public static List<Pedido> montarListaAguardaPgto() {
    List<Pedido> pedidos = new ArrayList<Pedido>(List.of(
      new Pedido(UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"), UUID.fromString("63a59178-39f8-4a28-a2c7-989a57ca7b54"), Status.AGUARDANDO_PAGAMENTO, Instant.now())
    ));

    return pedidos;
  }

  public static Map<Item, Integer> montarItensPedidos() {
    Map<Item, Integer> lista = new HashMap<Item, Integer>();
    Item item1 = new Item(Categoria.BEBIDA,	350,	UUID.fromString("23e52205-4d9d-41e6-a7f3-271af4f5316b"),	"REFRIGERANTE", "um delicioso refrigerante", "/var/app/imagens/refrigerante.jpg");
    Item item2 = new Item(Categoria.ACOMPANHAMENTO,	675,	UUID.fromString("f66d8bf8-f350-46ba-8893-902bfd3e556e"),	"BATATA FRITA", "batata-frita salgada", "/var/app/imagens/batata-frita.jpg");

    lista.put(item1, 1);
    lista.put(item2, 1);

    return lista;
  }

  private static Instant getDataCriacaoMock(String data) {
    String dateString = data;
    
    // Define the formatter to parse the string
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    
    // Parse the string to LocalDateTime
    LocalDateTime localDateTime = LocalDateTime.parse(dateString, formatter);
    
    // Convert LocalDateTime to Instant (using UTC ZoneOffset)
    Instant instant = localDateTime.atZone(ZoneOffset.systemDefault()).toInstant();
    
    return instant;
  }
   // The input string

}
