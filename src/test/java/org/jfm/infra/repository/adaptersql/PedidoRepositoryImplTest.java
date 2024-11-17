package org.jfm.infra.repository.adaptersql;

import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import org.jfm.domain.entities.Item;
import org.jfm.domain.entities.Pedido;
import org.jfm.domain.entities.enums.Status;
import org.jfm.domain.exceptions.EntityNotFoundException;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.factory.ItemFactory;
import org.jfm.factory.ItemPedidoFactory;
import org.jfm.factory.PedidoFactory;
import org.jfm.infra.repository.adaptersql.entities.ItemEntity;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.jfm.infra.repository.adaptersql.mapper.ItemMapper;
import org.jfm.infra.repository.adaptersql.mapper.PedidoMapper;
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
public class PedidoRepositoryImplTest {

  @Inject
  PedidoRepositoryImpl repository;

  @InjectMocks
  PedidoRepositoryImpl repositoryMock;

  @Mock
  EntityManager entityManager;

  @Mock
  PedidoMapper pedidoMapper;

  @Mock
  ItemMapper itemMapper;

  @Mock
  Query query;

  @Mock
  TypedQuery<PedidoEntity> typedQuery;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriar() {
    Pedido pedido = new Pedido();
    PedidoEntity pedidoEntity = new PedidoEntity();
    pedidoEntity.setItensPedidos(ItemPedidoFactory.montarItensPedido());
    when(pedidoMapper.toEntity(pedido, pedido.getId(), itemMapper)).thenReturn(pedidoEntity);
    
    repositoryMock.criar(pedido);

    verify(entityManager, times(1)).persist(any(PedidoEntity.class));
  }

  @Test
  public void testCriarException() {
    Pedido pedido = new Pedido();
    PedidoEntity pedidoEntity = new PedidoEntity();
    pedidoEntity.setItensPedidos(ItemPedidoFactory.montarItensPedido());
    when(pedidoMapper.toEntity(pedido, pedido.getId(), itemMapper)).thenReturn(pedidoEntity);

    doThrow(new PersistenceException()).when(entityManager).persist(any(PedidoEntity.class));

    assertThrows(ErrorSqlException.class, () -> repositoryMock.criar(pedido));
  }

  @Test
  public void testEditar() {
    when(entityManager.createNamedQuery("Pedido.update")).thenReturn(query);
    Pedido pedido = PedidoFactory.montar();

    repositoryMock.editar(pedido);

    verify(query, times(1)).setParameter("id", pedido.getId());
    verify(query, times(1)).setParameter("status", pedido.getStatus());
    verify(query, times(1)).executeUpdate();
  }

  @Test
  public void testEditarException() {
    when(entityManager.createNamedQuery("Pedido.update")).thenReturn(query);

    Pedido pedido = new Pedido();
    doThrow(new PersistenceException()).when(query).executeUpdate();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.editar(pedido));
  }

  @Test
  public void testListarException() {
    when(entityManager.createNamedQuery("Pedido.findAll", PedidoEntity.class)).thenReturn(typedQuery);
    
    doThrow(new PersistenceException()).when(typedQuery).getResultStream();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.listar());
  }

  @Test
  public void testBuscarPorIdException() {
    when(entityManager.createNamedQuery("Pedido.findById", PedidoEntity.class)).thenReturn(typedQuery);

    Pedido pedido = PedidoFactory.montar();
    doThrow(new PersistenceException()).when(typedQuery).getSingleResult();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.buscarPorId(pedido.getId()));
  }

  @Test
  public void testBuscarPorIdNoResultException() {
    when(entityManager.createNamedQuery("Pedido.findById", PedidoEntity.class)).thenReturn(typedQuery);
    
    Pedido pedido = PedidoFactory.montar();
    doThrow(new NoResultException()).when(typedQuery).getSingleResult();
    
    assertThrows(EntityNotFoundException.class, () -> repositoryMock.buscarPorId(pedido.getId()));
  }

  @Test
  public void testEditarItensDoPedido() {
    Pedido pedido = new Pedido();

    repositoryMock.editarItensDoPedido(pedido);

    verify(entityManager, times(1)).persist(pedido);
  }

  @Test
  public void testEditarItensDoPedidoException() {
    Pedido pedido = new Pedido();
    doThrow(new PersistenceException()).when(entityManager).persist(pedido);

    assertThrows(ErrorSqlException.class, () -> repositoryMock.editarItensDoPedido(pedido));
  }

  @Test
  public void testListarPorStatusException() {
    when(entityManager.createNamedQuery("Pedido.findByStatus", PedidoEntity.class)).thenReturn(typedQuery);
    
    doThrow(new PersistenceException()).when(typedQuery).getResultStream();

    assertThrows(ErrorSqlException.class, () -> repositoryMock.listarPorStatus(Status.AGUARDANDO_PAGAMENTO));
  }

  // @Test
  // public void testListarItensDoPedido() {
  //   // Pedido pedidoMontado = PedidoFactory.montar();
  //   Pedido pedido = new Pedido();
  //   pedido.setId(UUID.fromString("7f7e383e-58e2-43b6-a18a-f507003b45f8"));
  //   when(entityManager.createNamedQuery("Pedido.findByStatus", PedidoEntity.class)).thenReturn(typedQuery);
  //   query.setParameter("id", pedido.getId());
    
  //   PedidoEntity pedidoEntity = new PedidoEntity();
  //   pedidoEntity.setItensPedidos(ItemPedidoFactory.montarItensPedido());
  //   when(query.getSingleResult()).thenReturn(pedidoEntity);

  //   when(repositoryMock.listarItensDoPedido(pedido)).thenReturn(PedidoFactory.montarItensPedidos());
    
  //   // assertEquals(repository.listarItensDoPedido(pedido), PedidoFactory.montarItensPedidos());
  // }

}
