package org.jfm.controller.rest;

import java.util.List;
import java.util.UUID;

import org.jfm.controller.rest.dto.ItemCreateUpdateDto;
import org.jfm.controller.rest.mapper.ItemMapper;
import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.usecases.ItemUseCase;
import org.jfm.factory.ItemFactory;
import org.jfm.factory.dto.ItemCreateUpdateDtoFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.fasterxml.jackson.databind.ObjectMapper;

// import io.cucumber.java.en.Given;
// import io.cucumber.java.lu.an;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

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

  // BDD
  // Feature: Buscar um item específico pelo ID
  //   Scenario: Successfully retrieve an item by ID
  // @Test
  // public void testBuscarPorId() throws Exception {
  //   ObjectMapper mapper = new ObjectMapper();

  //   // Given the item with ID "257ae14b-8bb7-4a80-9a68-22197f72ff47" exists in the system
  //   UUID idItem = UUID.fromString("257ae14b-8bb7-4a80-9a68-22197f72ff47");
  //   Item item = ItemFactory.montarItem();

  //   Mockito.when(itemUseCase.buscarPorId(idItem)).thenReturn(item);

  //   // When I send a GET request to "/{id}" with the ID "257ae14b-8bb7-4a80-9a68-22197f72ff47"
  //   // Then the response status should be 200
  //   // And the response body should contain the item details
  //   given()
  //   .contentType(ContentType.JSON)
  //   .when().pathParam("id", item.getId())
  //   .get("/itens/{id}")
  //   .then().statusCode(200)
  //   .body(is(mapper.writeValueAsString(item)));
  // }

  // @Test
  // public void testRemoverException() {
  //   given()
  //     .contentType(ContentType.JSON)
  //     .when().pathParam("id", "")
  //     .delete("/itens/{id}")
  //     .then().statusCode(400);
  // }

}
