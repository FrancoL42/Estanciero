package ar.edu.utn.frc.tup.lciii.service.impl;

import ar.edu.utn.frc.tup.lciii.entity.GamePropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.converts.PropertyConvert;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.*;
import ar.edu.utn.frc.tup.lciii.repositories.PropertyRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.PropertyRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.service.PropertyService;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PropertyServiceImpl implements PropertyService {
    PropertyRepository repository = new PropertyRepositoryImpl();
    @Override
    public List<Property> getAllProperty(Long IdGame) {
        List<PropertyEntity> propertyEntities = repository.getAllProperties();
        List<Property> properties = new ArrayList<>();
        for(PropertyEntity entity : propertyEntities){
            AbstractProperty property = null;
            property = getAbstractProperty(entity, property);
            properties.add(property);
        }
        return properties;
    }

    public AbstractProperty getAbstractProperty(PropertyEntity entity, AbstractProperty property) {
        switch (entity.getPropertyType().getType()){
            case "Campos" :
                property = FieldProperty.builder().build();
                property.setPropertyId(entity.getId());
                property.setPropertyValue(entity.getRentValue());
                property.setRentValue(entity.getRentValue());
                property.setPropertyTypeId(entity.getPropertyType().getId());
            case "Empresas" :
                property = new CompanyProperty();
                property.setPropertyId(entity.getId());
                property.setPropertyValue(entity.getRentValue());
                property.setRentValue(entity.getRentValue());
                property.setPropertyTypeId(entity.getPropertyType().getId());
            case "Ferrocarril" :
                property = new RailroadProperty();
                property.setPropertyId(entity.getId());
                property.setPropertyValue(entity.getRentValue());
                property.setRentValue(entity.getRentValue());
                property.setPropertyTypeId(entity.getPropertyType().getId());
        }
        return property;
    }

    @Override
    public List<AbstractProperty> getAllProperty(Long idGame, List<PlayerImplement> players) {
        List<GamePropertyEntity> propertyEntities = repository.getAllPropertiesByGameId(idGame);
        List<AbstractProperty> properties = new ArrayList<>();
        for (GamePropertyEntity propertyEntity : propertyEntities) {
            AbstractProperty abstractProperty = PropertyConvert.ConvertToAbstractProperty(propertyEntity);
           if(propertyEntity.getPlayer() != null) {
               Optional<PlayerImplement> playerImplement = players.stream()
                       .filter(p -> p.getPlayerID().equals(propertyEntity.getPlayer().getId()))
                       .findAny();
               playerImplement.ifPresent(abstractProperty::setOwner);
                if(playerImplement.isPresent()){
                    playerImplement.get().getProperties().add(abstractProperty);

                }
           }
           properties.add(abstractProperty);
        }
        return properties;
       
    }

//    @Override
//    public void putProperty() {
//
//    }
//
//    @Override
//    public void postProperty() {
//
//    }
}
