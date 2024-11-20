package org.jfm.controller.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.ObjectUtils.Null;
import org.jfm.controller.rest.dto.ItemCreateUpdateDto;
import org.jfm.controller.rest.mapper.ItemMapper;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.exceptions.ErrosSistemaEnum;
import org.jfm.domain.exceptions.ParamException;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.factory.ItemFactory;
import org.jfm.factory.dto.ItemCreateUpdateDtoFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;

import static io.restassured.RestAssured.given;
// import static io.restassured.RestAssured.when;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@QuarkusTest
public class ItemResourceTest {

  @Inject
  ItemResource itemResource;

  @InjectMocks
  ItemResource itemResourceMock;

  @Mock
  ItemUseCase itemUseCase;

  @Mock
  ItemMapper itemMapper;

  @BeforeEach
  public void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriar() {
    ItemCreateUpdateDto dto = ItemCreateUpdateDtoFactory.montar();
    Item item = new Item();
    
    Mockito.when(itemMapper.toDomain(dto)).thenReturn(item);
    Mockito.when(itemUseCase.criar(item)).thenReturn(item.getId());

    given()
      .contentType(ContentType.JSON)
      .body(dto)
      .when()
      .post("/itens")
      .then().statusCode(201);
  }

  @Test
  public void testBuscar() {
    List<Item> itens = ItemFactory.montarListaItens();

    Mockito.when(itemUseCase.listar()).thenReturn(itens);

    given()
    .contentType(ContentType.JSON)
    .when()
    .get("/itens")
    .then().statusCode(200);
  }

  @Test
  public void testBuscarCategoria() {
    List<Item> itens = ItemFactory.montarListaItensCategoria();

    Mockito.when(itemUseCase.listar()).thenReturn(itens);

    given()
    .contentType(ContentType.JSON)
    .when().queryParam("categoria", Categoria.BEBIDA)
    .get("/itens")
    .then().statusCode(200);
  }

  @Test
  public void testBuscarPorId() {
    Item item = ItemFactory.montarItem();

    Mockito.when(itemUseCase.buscarPorId(item.getId())).thenReturn(item);

    given()
    .contentType(ContentType.JSON)
    .when().pathParam("id", item.getId())
    .get("/itens/{id}")
    .then().statusCode(200);
  }

  @Test
  public void testEditar() {
    Item itemMontado = ItemFactory.montarItem();
    ItemCreateUpdateDto dto = ItemCreateUpdateDtoFactory.montar();
    Item item = new Item();
    
    Mockito.when(itemMapper.toDomain(dto)).thenReturn(item);
    item.setId(itemMontado.getId());

    given()
      .contentType(ContentType.JSON)
      .body(dto)
      .when().pathParam("id", itemMontado.getId())
      .put("/itens/{id}")
      .then().statusCode(200);
  }

  @Test
  public void testRemover() {
    given()
      .contentType(ContentType.JSON)
      .when().pathParam("id", UUID.fromString("6907dc62-e579-4178-ba30-3d7e4cea021d"))
      .delete("/itens/{id}")
      .then().statusCode(200);
  }

  // @Test
  // public void testRemoverException() {
  //   given()
  //     .contentType(ContentType.JSON)
  //     .when().pathParam("id", "")
  //     .delete("/itens/{id}")
  //     .then().statusCode(400);
  // }

}
