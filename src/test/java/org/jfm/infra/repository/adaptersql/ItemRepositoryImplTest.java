package org.jfm.infra.repository.adaptersql;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.enums.Categoria;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.factory.ItemFactory;
import org.jfm.infra.repository.adaptersql.entities.ItemEntity;
import org.jfm.infra.repository.adaptersql.mapper.ItemMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@QuarkusTest
public class ItemRepositoryImplTest {
  
  @Inject
  ItemRepositoryImpl repository;

  @InjectMocks
  ItemRepositoryImpl repositoryMock;

  @Mock
  EntityManager entityManager;

  @Mock
  ItemMapper itemMapper;

  @Mock
  Query query;

  @Mock
  TypedQuery<ItemEntity> typedQuery;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriar() {
    Item item = new Item();
    when(itemMapper.toEntity(item)).thenReturn(new ItemEntity());

    repositoryMock.criar(item);

    verify(entityManager, times(1)).persist(any(ItemEntity.class));
  }

  @Test
  public void testCriarException() {
    Item item = new Item();
    when(itemMapper.toEntity(item)).thenReturn(new ItemEntity());

    doThrow(new PersistenceException()).when(entityManager).persist(any(ItemEntity.class));

    assertThrows(ErrorSqlException.class, () -> repositoryMock.criar(item));
  }

  @Test
  public void testEditar() {
    when(entityManager.createNamedQuery("Item.update")).thenReturn(query);
    Item item = ItemFactory.montarItem();

    repositoryMock.editar(item);

    verify(query, times(1)).setParameter("id", item.getId());
    verify(query, times(1)).setParameter("nome", item.getNome());
    verify(query, times(1)).setParameter("preco", item.getPreco());
    verify(query, times(1)).setParameter("categoria", item.getCategoria());
    verify(query, times(1)).setParameter("descricao", item.getDescricao());
    verify(query, times(1)).setParameter("imagem", item.getImagem());
    verify(query, times(1)).executeUpdate();
  }

  
  @Test
  public void testEditarException() {
    when(entityManager.createNamedQuery("Item.update")).thenReturn(query);

    Item item = new Item();
    doThrow(new PersistenceException()).when(query).executeUpdate();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.editar(item));
  }

  @Test
  public void testRemover() {
    when(entityManager.createNamedQuery("Item.delete")).thenReturn(query);

    Item item = ItemFactory.montarItem();

    repositoryMock.remover(item);
    verify(query, times(1)).setParameter("id", item.getId());
    verify(query, times(1)).executeUpdate();
  }

  @Test
  public void testRemoverException() {
    when(entityManager.createNamedQuery("Item.delete")).thenReturn(query);

    Item item = new Item();
    doThrow(new PersistenceException()).when(query).executeUpdate();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.remover(item));
  }

  @Test
  public void testBuscarPorIdException() {
    when(entityManager.createNamedQuery("Item.findById", ItemEntity.class)).thenReturn(typedQuery);

    Item item = ItemFactory.montarItem();
    doThrow(new PersistenceException()).when(typedQuery).getSingleResult();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.buscarPorId(item.getId()));
  }
  
  @Test
  public void testBuscarPorIdNoResultException() {
    when(entityManager.createNamedQuery("Item.findById", ItemEntity.class)).thenReturn(typedQuery);
    
    Item item = ItemFactory.montarItem();
    doThrow(new NoResultException()).when(typedQuery).getSingleResult();
    
    assertThrows(EntityNotFoundException.class, () -> repositoryMock.buscarPorId(item.getId()));
  }
  
  @Test
  public void testListarException() {
    when(entityManager.createNamedQuery("Item.findAll", ItemEntity.class)).thenReturn(typedQuery);
    
    doThrow(new PersistenceException()).when(typedQuery).getResultStream();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.listar());
  }
  
  @Test
  public void testListarPorCategoriaException() {
    when(entityManager.createNamedQuery("Item.findByCategoria", ItemEntity.class)).thenReturn(typedQuery);
    
    doThrow(new PersistenceException()).when(typedQuery).getResultStream();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.listarPorCategoria(Categoria.LANCHE));
  }

}
