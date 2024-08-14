package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entity.*;

import java.util.List;

public interface GameRepository {
    GameEntity getGame(Long gameId);
    List<GameEntity> getGamesByUserId(Long userId);
    <T> void save(List<T> entities);
    GameEntity save(GameEntity gameEntity, List<CardGameEntity> cards, List<GamePropertyEntity> properties, List<PlayerEntity> players);
}
