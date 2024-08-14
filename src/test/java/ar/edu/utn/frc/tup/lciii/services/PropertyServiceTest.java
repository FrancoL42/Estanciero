package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.entity.GamePropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.PropertyTypeEntity;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.repositories.PropertyRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.PropertyRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.service.PropertyService;
import ar.edu.utn.frc.tup.lciii.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PropertyServiceTest {

    @Mock
    PropertyRepository propertyRepository;
    PropertyService propertyService = new PropertyServiceImpl();


    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getAllPropertyTest(){
        List<PropertyEntity> propertyList = new ArrayList<>();
        PropertyEntity property = new PropertyEntity();
        propertyList.add(property);
        PropertyRepository propertyRepository = mock(PropertyRepository.class);
        when(propertyRepository.getAllProperties()).thenReturn(propertyList);
        assertNotNull(propertyService.getAllProperty(1L));
    }
    @Test
    public void getAbstractProperty(){

    }
    @Test
    public void getAllPropertyTest2(){
        List<GamePropertyEntity> gamePropertyEntities = new ArrayList<>();
        List<PlayerImplement> playerImplements = new ArrayList<>();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplements.add(playerImplement);
        Long id = Long.parseLong("1");
        when(propertyRepository.getAllPropertiesByGameId(id)).thenReturn(gamePropertyEntities);
        assertNotNull(propertyService.getAllProperty(id,playerImplements));
    }
}
