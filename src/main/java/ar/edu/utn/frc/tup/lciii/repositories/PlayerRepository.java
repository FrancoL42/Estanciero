package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entity.PropertyEntity;

import java.util.List;

public interface PlayerRepository {
    List<PlayerEntity> getAllPlayersByGameId(Long gameId);
   // List<PlayerEntity> savePlayers(List<PlayerEntity> players);
    PlayerEntity getById(Long id);
}
