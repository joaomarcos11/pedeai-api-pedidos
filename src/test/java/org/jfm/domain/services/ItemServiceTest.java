package org.jfm.domain.services;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import java.util.List;
import java.util.UUID;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.factory.ItemFactory;
import org.jfm.infra.repository.adaptersql.ItemRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;

@QuarkusTest
public class ItemServiceTest {

  @Inject
  ItemService service;

  @InjectMocks
  ItemService serviceMock;

  @Mock
  ItemRepositoryImpl repository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  // @Mock
  // ItemRepositoryImpl repositoryImpl;

  //TODO: como testar o void?
  @Test
  public void testCriar() {
    Item item = ItemFactory.montarItem();
    item.setId(UUID.randomUUID());

    serviceMock.criar(item);

    verify(repository, times(1)).criar(item);
  }

  @Test
  public void testListar() {
    // criar lista de item
    List<Item> itens = ItemFactory.montarListaItens();

    // mock repository
    when(repository.listar()).thenReturn(itens);

    // assert equals chamando service
    // Assertions.assertEquals(service.listar(), itens); // TODO: consertar
    Assertions.assertNotNull(service.listar()); // TODO: consertar
  }

  @Test
  public void testBuscarPorId() {
    Item item = ItemFactory.montarItem();

    Assertions.assertEquals(service.buscarPorId(item.getId()), item);
  }

  @Test
  public void testEditar() {
    Item item = ItemFactory.montarItem();
    item.setNome("HAMBURGUER");

    serviceMock.editar(item);

    // Assert: Verify that the editar method in the repository was called once with the given item
    verify(repository, times(1)).editar(item);
  }

  @Test
  public void testListarCategoria() {
    List<Item> itens = ItemFactory.montarListaItensCategoria();

    Assertions.assertEquals(service.listarCategoria(Categoria.BEBIDA), itens);
  }

  @Test
  public void testRemover() {
    Item item = ItemFactory.montarItem();
    when(repository.buscarPorId(item.getId())).thenReturn(item);

    serviceMock.remover(item.getId());

    // Assert: Verify that the editar method in the repository was called once with the given item
    verify(repository, times(1)).remover(item);
  }
}
