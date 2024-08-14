package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.GamePropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PropertyRepositoryImplTest {

    @Mock
    private Session session;

    @Mock
    private Transaction transaction;

    @Mock
    private PropertyRepositoryImpl propertyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        propertyRepository = new PropertyRepositoryImpl();
    }

    @Test
    void testGetAll() {
        List<PropertyEntity> properties = new ArrayList<>();
        properties.add(new PropertyEntity());

        Query query = mock(Query.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.list()).thenReturn(properties);

        List<PropertyEntity> result = propertyRepository.getAllProperties();

        assertNotEquals(properties.size(), result.size());
    }

    @Test
    void testGetAllByGame(){
       List<GamePropertyEntity> gameProperties = new ArrayList<>();
       gameProperties.add(new GamePropertyEntity());

        Query query = mock(Query.class);
        when(session.beginTransaction()).thenReturn(transaction);
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.list()).thenReturn(gameProperties);
        List<GamePropertyEntity> result = propertyRepository.getAllPropertiesByGameId(1L);
        assertNotEquals(gameProperties.size(),result.size());
    }

    @Test
    void testGetById(){
        Long id = 1L;
        PropertyEntity property = new PropertyEntity();
        property.setId(id);
        PropertyEntity result = propertyRepository.getById(id);
        assertNotNull(result);
        assertEquals(property.getId(), result.getId());

    }

    @Disabled
    @Test
    void testSave(){
//        GamePropertyEntity gameProperty = new GamePropertyEntity();
//        gameProperty.setId(1L);
//        when(session.beginTransaction()).thenReturn(transaction);
//        propertyRepository.saveProperties(gameProperty);
//        verify(session).save(gameProperty);
//        verify(transaction).commit();
    }
}
