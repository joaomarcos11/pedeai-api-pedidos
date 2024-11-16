// package org.jfm.controller.rest;

// import java.util.ArrayList;
// import java.util.List;
// import java.util.UUID;

// import org.jfm.controller.rest.dto.ItemCreateUpdateDto;
// import org.jfm.controller.rest.mapper.ItemMapper;
// import org.jfm.domain.entities.Item;
// import org.jfm.domain.entities.enums.Categoria;
// import org.jfm.domain.usecases.ItemUseCase;
// import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.Test;

// import io.quarkus.test.InjectMock;
// import io.quarkus.test.common.http.TestHTTPEndpoint;
// import io.quarkus.test.junit.QuarkusTest;
// import jakarta.inject.Inject;
// import jakarta.ws.rs.core.Response;

// import static io.restassured.RestAssured.given;
// import static io.restassured.RestAssured.when;
// import static org.hamcrest.CoreMatchers.is;

// import static org.mockito.Mockito.when;

// @QuarkusTest
// @TestHTTPEndpoint(ItemResource.class)
// public class ItemResourceTest {
  
//   @InjectMock // mockar
//   ItemUseCase itemUseCase;

//   @Inject // vai realmente chamar
//   ItemMapper itemMapper;

//   @Test
//   public void testCriar() {
//     ItemCreateUpdateDto item = new ItemCreateUpdateDto("nome", 1, Categoria.LANCHE, "descricao", "imagem");
//     Item itemEntity = itemMapper.toDomain(item);
    
//     UUID idItem = UUID.randomUUID();
//     when(itemUseCase.criar(itemEntity)).thenReturn(idItem);

//     given()
//       .when().post()
//       .then()
//         .statusCode(201)
//         .body(is(idItem));
//   }

//   @Test
//   public void testBuscar() {
//     List<Item> itens = new ArrayList<Item>();
//     when(itemUseCase.listar()).thenReturn(itens);

//     given()
//       .when().get()
//       .then()
//         .statusCode(200)
//         .body(is(itens));
//   }

//   // @Inject
//   // GreetingService service;

//   // @Test
//   // public void testGreetingService() {
//   //     Assertions.assertEquals("hello Quarkus", service.greeting("Quarkus"))
//   // }

//   // @Test
//   // public void testHelloEndpoint() {
//   //     given()
//   //       .when().get("/hello")
//   //       .then()
//   //          .statusCode(200)
//   //          .body(is("hello"));
//   // }

//   // @Test
//   // public void testGreetingEndpoint() {
//   //     String uuid = UUID.randomUUID().toString();
//   //     given()
//   //       .pathParam("name", uuid)
//   //       .when().get("/hello/greeting/{name}")
//   //       .then()
//   //         .statusCode(200)
//   //         .body(is("hello " + uuid));
//   // }
// }
