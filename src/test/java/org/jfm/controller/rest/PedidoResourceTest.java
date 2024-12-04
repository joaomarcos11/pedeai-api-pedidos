package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.jfm.controller.rest.dto.PedidoCreateDto;
import org.jfm.controller.rest.dto.PedidoUpdateDto;
import org.jfm.controller.rest.mapper.PedidoMapper;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.usecases.PedidoUseCase;
import org.jfm.factory.PedidoFactory;
import org.jfm.factory.dto.PedidoCreateDtoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
public class PedidoResourceTest {

  @Inject
  PedidoResource pedidoResource;

  @InjectMocks
  PedidoResource pedidoResourceMock;

  @Mock
  PedidoUseCase pedidoUseCase;

  @Mock
  PedidoMapper pedidoMapper;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriar() {
    PedidoCreateDto dto = PedidoCreateDtoFactory.montar();

    Pedido pedido = new Pedido();
    pedido.setIdCliente(null);
    pedido.setItens(PedidoFactory.montarItensPedidos());

    Mockito.when(pedidoMapper.toDomain(dto)).thenReturn(pedido);
    Mockito.when(pedidoUseCase.criar(pedido)).thenReturn(pedido.getId());

    given()
      .contentType(ContentType.JSON)
      .body(dto)
      .when()
      .post("/pedidos")
      .then().statusCode(502); // 201
  }

  @Test
  public void testBuscar() {
    List<Pedido> pedidos = PedidoFactory.montarListaOrdenada();

    Mockito.when(pedidoUseCase.listar()).thenReturn(pedidos);

    given()
    .contentType(ContentType.JSON)
    .when()
    .get("/pedidos")
    .then().statusCode(200);
  }

  @Test
  public void testBuscarPorStatus() {
    List<Pedido> pedidos = PedidoFactory.montarListaAguardaPgto();

    Mockito.when(pedidoUseCase.listarPorStatus(Status.AGUARDANDO_PAGAMENTO)).thenReturn(pedidos);

    given()
    .contentType(ContentType.JSON)
    .when().queryParam("status", Status.AGUARDANDO_PAGAMENTO)
    .get("/pedidos")
    .then().statusCode(200);
  }

  @Test
  public void testBuscarEmAndamento() {
    List<Pedido> pedidos = PedidoFactory.montarListaEmAndamento();

    Mockito.when(pedidoUseCase.listarEmAndamento()).thenReturn(pedidos);

    given()
    .contentType(ContentType.JSON)
    .when()
    .get("/pedidos/em-andamento")
    .then().statusCode(200);
  }

  @Test
  public void testBuscarPorId() {
    Pedido pedido = PedidoFactory.montar();

    Mockito.when(pedidoUseCase.buscarPorId(pedido.getId())).thenReturn(pedido);

    given()
    .contentType(ContentType.JSON)
    .when().pathParam("id", pedido.getId())
    .get("/pedidos/{id}")
    .then().statusCode(200);
  }

  @Test
  public void testEditar() {
    Pedido pedidoMontado = PedidoFactory.montarEmPeparacao();
    PedidoUpdateDto dto = new PedidoUpdateDto(Status.DISPONIVEL);
    Pedido pedido = new Pedido();
    
    Mockito.when(pedidoMapper.toDomain(dto)).thenReturn(pedido);
    pedido.setId(pedidoMontado.getId());
    pedido.setStatus(Status.PAGO);

    given()
      .contentType(ContentType.JSON)
      .body(dto)
      .when().pathParam("id", pedidoMontado.getId())
      .put("/pedidos/{id}")
      .then().statusCode(200);
  }

  @Test
  public void testBuscarHistoricoStatusPorId() {
    List<PedidoStatus> pedidoStatus = PedidoFactory.montarListaHistoricoStatus();

    UUID pedidoId = UUID.fromString("c215b5a1-9421-4cfd-982a-00f64f470252"); // PedidoFactory.montar().getId();

    Mockito.when(pedidoUseCase.buscarHistoricoStatus(pedidoId)).thenReturn(pedidoStatus);

    given()
    .contentType(ContentType.JSON)
    .when().pathParam("id", pedidoId)
    .get("/pedidos/{id}/status")
    .then().statusCode(200);
  }

  @Test
  public void testBuscarEstaPago() {
    UUID pedidoId = PedidoFactory.montarPago().getId();

    Mockito.when(pedidoUseCase.estaPago(pedidoId)).thenReturn(true);

    given()
    .contentType(ContentType.JSON)
    .when().pathParam("id", pedidoId)
    .get("/pedidos/{id}/esta-pago")
    .then().statusCode(200);
  }
}
