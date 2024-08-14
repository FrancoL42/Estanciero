package ar.edu.utn.frc.tup.lciii.service.impl;

import ar.edu.utn.frc.tup.lciii.entity.SquareEntity;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.square.*;
import ar.edu.utn.frc.tup.lciii.repositories.SquareRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.SquareRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.service.SquareService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SquareServiceImpl implements SquareService {
    SquareRepository squareRepository = new SquareRepositoryImpl();
    @Override
    public List<Square> getAllSquare(List<AbstractProperty> properties) {
        List<SquareEntity> squareEntities = squareRepository.getAllSquares();
        List<Square> squares = new ArrayList<>();
        for (SquareEntity entity : squareEntities) {
            AbstractSquare square = null;
            switch (entity.getSquareType().getDescription()) {
                case "Propiedades":
                    square = new PropertySquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    Optional<AbstractProperty> property = properties.stream()
                            .filter(p -> p.getIdEntityPropertyConfig().equals(entity.getProperty().getId()))
                            .findAny();
                    if(property.isPresent()){
                        ((PropertySquare) square).setProperty(property.get());
                    }
                    break;
                case "Suerte":
                    square = new LuckSquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    break;
                case "Destino":
                    square = new DestinySquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    break;
                case "Carcel":
                    square = new JailSquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    break;
                case "Impuestos", "Premio":
                    square = new PrizePenaltySquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    ((PrizePenaltySquare) square).setPrizePenaltyAmount(entity.getPrizeAmount());
                    break;
                case "Salida":
                    square = new StartingSquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    break;
                case "Descanso":
                    square = new RestSquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    break;
                case "Marche Preso":
                    square = new GoToJailSquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    break;
                case "Libre Estacionamiento":
                    square = new FreeParkingSquare();
                    square.setSquareId(entity.getId());
                    square.setNumberSquare(entity.getNumber());
                    break;
            }
            squares.add(square);
        }
        return squares;
    }

//    @Override
//    public void postSquare() {
//
//    }
//
//    @Override
//    public void putSquare() {
//
//    }
}
