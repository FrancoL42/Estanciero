package ar.edu.utn.frc.tup.lciii.entity.converts;

import ar.edu.utn.frc.tup.lciii.entity.*;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.RailroadProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PropertyConvertTest {

    @Test
    public void ConvertToAbstractPropertyFieldProperty(){
        GamePropertyEntity gamePropertyEntity = new GamePropertyEntity();
        gamePropertyEntity.setId(1L);
        gamePropertyEntity.setFarmCount(3);
        gamePropertyEntity.setIsMortgaged(false);

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setPropertyType(new PropertyTypeEntity(1L, "Field"));
        propertyEntity.setProvince(new ProvinceEntity("Buenos Aires"));
        propertyEntity.setZone(new ZoneEntity("Zona 1"));
        propertyEntity.setFarmBuyValue(500);
        propertyEntity.setNumberOfZonesInProvince(5);
        propertyEntity.setDescription("Campo en Buenos Aires");
        propertyEntity.setRentValue(150);
        propertyEntity.setBuyValue(2000);
        propertyEntity.setId(100L);

        gamePropertyEntity.setProperty(propertyEntity);

        AbstractProperty result = PropertyConvert.ConvertToAbstractProperty(gamePropertyEntity);

        assertNotNull(result);
        assertTrue(result instanceof FieldProperty);
        assertEquals("null null", result.getName());
        assertEquals(3, ((FieldProperty) result).getFarmCount());
        assertEquals(1L, result.getPropertyId());
        assertEquals(150, result.getRentValue());
        assertEquals(2000, result.getPropertyValue());
        assertEquals(100L, result.getIdEntityPropertyConfig());
        assertTrue(result.getState() instanceof FreePropertyState);
    }

    @Test
    public void ConvertToGamePropertyEntityFieldProperty(){
        FieldProperty fieldProperty = FieldProperty.builder()
                .province("Buenos Aires")
                .zone("Zona 1")
                .farmCount(3)
                .farmValue(500)
                .cantPropertiesZones(5)
                .build();
        fieldProperty.setPropertyId(1L);
        fieldProperty.setRentValue(150);
        fieldProperty.setPropertyValue(2000);
        fieldProperty.setIdEntityPropertyConfig(100L);
        fieldProperty.setState(new FreePropertyState());

        GamePropertyEntity result = PropertyConvert.ConvertToGamePropertyEntity(fieldProperty);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals(3, result.getFarmCount());
        assertFalse(result.getIsMortgaged());
        assertNull(result.getPlayer());
    }

    @Test
    public void ConvertToAbstractPropertyCompanyProperty(){
        GamePropertyEntity gamePropertyEntity = new GamePropertyEntity();
        gamePropertyEntity.setId(2L);
        gamePropertyEntity.setIsMortgaged(true);

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setPropertyType(new PropertyTypeEntity(2L, "Company"));
        propertyEntity.setDescription("Compañía de servicios");
        propertyEntity.setRentValue(300);
        propertyEntity.setBuyValue(5000);
        propertyEntity.setId(200L);

        gamePropertyEntity.setProperty(propertyEntity);

        AbstractProperty result = PropertyConvert.ConvertToAbstractProperty(gamePropertyEntity);

        assertNotNull(result);
        assertTrue(result instanceof CompanyProperty);
        assertEquals("Compañía de servicios", result.getName());
        assertEquals(2L, result.getPropertyId());
        assertEquals(300, result.getRentValue());
        assertEquals(5000, result.getPropertyValue());
        assertEquals(200L, result.getIdEntityPropertyConfig());
        assertTrue(result.getState() instanceof MortgagePropertyState);
    }

    @Test
    public void ConvertToAbstractPropertyRailroadProperty(){
        GamePropertyEntity gamePropertyEntity = new GamePropertyEntity();
        gamePropertyEntity.setId(3L);
        gamePropertyEntity.setIsMortgaged(false);

        PropertyEntity propertyEntity = new PropertyEntity();
        propertyEntity.setPropertyType(new PropertyTypeEntity(3L, "Railroad"));
        propertyEntity.setDescription("Forrocarril Central");
        propertyEntity.setRentValue(200);
        propertyEntity.setBuyValue(4000);
        propertyEntity.setId(300L);

        gamePropertyEntity.setProperty(propertyEntity);

        AbstractProperty result = PropertyConvert.ConvertToAbstractProperty(gamePropertyEntity);

        assertNotNull(result);
        assertTrue(result instanceof RailroadProperty);
        assertEquals("Forrocarril Central", result.getName());
        assertEquals(3L, result.getPropertyId());
        assertEquals(200, result.getRentValue());
        assertEquals(4000, result.getPropertyValue());
        assertEquals(300L, result.getIdEntityPropertyConfig());
        assertTrue(result.getState() instanceof FreePropertyState);
    }

    @Test
    public void ConvertToGamePropertyEntityCompanyProperty(){
        CompanyProperty companyProperty = new CompanyProperty();
        companyProperty.setName("Compañía de Servicios");
        companyProperty.setPropertyId(2L);
        companyProperty.setRentValue(300);
        companyProperty.setPropertyValue(5000);
        companyProperty.setIdEntityPropertyConfig(200L);
        companyProperty.setState(new MortgagePropertyState());

        GamePropertyEntity result = PropertyConvert.ConvertToGamePropertyEntity(companyProperty);

        assertNotNull(result);
        assertEquals(2L, result.getId());
        assertTrue(result.getIsMortgaged());
        assertNull(result.getPlayer());
    }

    @Test
    public void ConvertToGamePropertyEntityRailroadProperty(){
        RailroadProperty railroadProperty = new RailroadProperty();
        railroadProperty.setName("Ferrocarril Central");
        railroadProperty.setPropertyId(3L);
        railroadProperty.setRentValue(200);
        railroadProperty.setPropertyValue(4000);
        railroadProperty.setIdEntityPropertyConfig(300L);
        railroadProperty.setState(new FreePropertyState());

        GamePropertyEntity result = PropertyConvert.ConvertToGamePropertyEntity(railroadProperty);

        assertNotNull(result);
        assertEquals(3L, result.getId());
        assertFalse(result.getIsMortgaged());
        assertNull(result.getPlayer());

    }
}