package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entity.GamePropertyEntity;
import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;

import java.util.List;

public interface PropertyRepository {
    List<GamePropertyEntity> getAllPropertiesByGameId(Long gameId);
    List<PropertyEntity> getAllProperties();
//    List<PropertyEntity> saveProperties(List<PropertyEntity> properties);
    PropertyEntity getById(Long id);
}
