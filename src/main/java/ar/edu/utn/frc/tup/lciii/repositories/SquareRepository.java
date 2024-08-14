package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entity.PlayerSquareEntity;
import ar.edu.utn.frc.tup.lciii.entity.SquareEntity;

import java.util.List;

public interface SquareRepository {

    List<SquareEntity> getAllSquares();
//    List<PlayerSquareEntity> getAllSquaresByGameId(Long gameId);
   // List<SquareEntity> saveSquares(List<SquareEntity> squares);

    SquareEntity getByNumber(Integer number);
}
