package org.jfm.infra.repository.adaptersql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.jfm.domain.entities.PedidoStatus;
import org.jfm.domain.exceptions.ErrorSqlException;
import org.jfm.factory.PedidoStatusFactory;
import org.jfm.infra.repository.adaptersql.entities.ItemEntity;
import org.jfm.infra.repository.adaptersql.entities.PedidoEntity;
import org.jfm.infra.repository.adaptersql.entities.PedidoStatusEntity;
import org.jfm.infra.repository.adaptersql.mapper.PedidoStatusMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@QuarkusTest
public class PedidoStatusRepositoryImplTest {
  
  @Inject
  PedidoStatusRepositoryImpl repository;

  @InjectMocks
  PedidoStatusRepositoryImpl repositoryMock;

  @Mock
  EntityManager entityManager;

  @Mock
  PedidoStatusMapper pedidoStatusMapper;

  @Mock
  Query query;

  @Mock
  TypedQuery<PedidoStatusEntity> typedQuery;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testCriar() {
    PedidoStatus pedidoStatus = PedidoStatusFactory.montar();
    PedidoStatusEntity pedidoStatusEntity = new PedidoStatusEntity();
    PedidoEntity pedidoEntity = new PedidoEntity();

    when(pedidoStatusMapper.toEntity(pedidoStatus)).thenReturn(pedidoStatusEntity);
    when(entityManager.find(PedidoEntity.class, pedidoStatus.getIdPedido())).thenReturn(pedidoEntity);

    repositoryMock.criar(pedidoStatus);

    verify(entityManager, times(1)).persist(pedidoStatusEntity);
  }

  @Test
  public void testCriarException() {
    PedidoStatus pedidoStatus = PedidoStatusFactory.montar();
    PedidoStatusEntity pedidoStatusEntity = new PedidoStatusEntity();
    PedidoEntity pedidoEntity = new PedidoEntity();

    when(pedidoStatusMapper.toEntity(pedidoStatus)).thenReturn(pedidoStatusEntity);
    when(entityManager.find(PedidoEntity.class, pedidoStatus.getIdPedido())).thenReturn(pedidoEntity);

    doThrow(new PersistenceException()).when(entityManager).persist(any(PedidoStatusEntity.class));

    assertThrows(ErrorSqlException.class, () -> repositoryMock.criar(pedidoStatus));
  }

  @Test
  public void testListarPorPedidoId() {
    UUID pedidoId = UUID.randomUUID();

    // Mock the query and result
    List<PedidoStatusEntity> entitiesMock = new ArrayList<PedidoStatusEntity>();
    PedidoStatusEntity entity = new PedidoStatusEntity();
    entitiesMock.add(entity);

      when(entityManager.createNamedQuery("PedidoStatus.findAllByPedidoId", PedidoStatusEntity.class))
          .thenReturn(typedQuery);
      when(typedQuery.setParameter("pedido_id", pedidoId)).thenReturn(typedQuery);
      when(typedQuery.getResultList()).thenReturn(entitiesMock);
      when(pedidoStatusMapper.toDomain(entity)).thenReturn(new PedidoStatus());

    // Execute the method
    List<PedidoStatus> lista = repositoryMock.listarPorPedidoId(pedidoId);

    // Verify and assert
    assertNotNull(lista);
    assertEquals(1, lista.size());
    verify(entityManager).createNamedQuery("PedidoStatus.findAllByPedidoId", PedidoStatusEntity.class);
  }

  @Test
  public void testListarPorPedidoIdException() {
    UUID pedidoId = UUID.randomUUID();

    when(entityManager.createNamedQuery("PedidoStatus.findAllByPedidoId", PedidoStatusEntity.class))
        .thenThrow(new PersistenceException());


    assertThrows(ErrorSqlException.class, () -> repositoryMock.listarPorPedidoId(pedidoId));
  }
}


