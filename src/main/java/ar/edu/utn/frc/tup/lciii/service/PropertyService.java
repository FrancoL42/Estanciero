package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;

import java.util.List;

public interface PropertyService {
    /*
    Busca todas las propiedades filtrando un juego en especifico.
     */
    List<Property> getAllProperty(Long IdGame);

    List<AbstractProperty> getAllProperty(Long IdGame, List<PlayerImplement> players);
//    void putProperty();
//    void postProperty();
}
