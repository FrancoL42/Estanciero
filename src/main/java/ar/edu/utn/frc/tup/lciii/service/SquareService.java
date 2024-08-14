package ar.edu.utn.frc.tup.lciii.service;

import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.square.Square;

import java.util.List;

public interface SquareService {
    List<Square> getAllSquare(List<AbstractProperty> properties);

//    void postSquare();
//    void putSquare();
}
