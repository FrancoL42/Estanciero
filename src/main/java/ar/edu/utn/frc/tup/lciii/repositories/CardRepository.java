package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entity.CardEntity;
import ar.edu.utn.frc.tup.lciii.entity.CardGameEntity;

import java.util.List;

public interface CardRepository {
    List<CardEntity> getChanceCards();
    List<CardEntity> getDestinyCards();
    List<CardGameEntity> getChanceCardsByGameId(Long Id);
    List<CardGameEntity> getDestinyCardsByGameId(Long Id);
    List<CardEntity> getCards(String description);
    List<CardGameEntity> getCards(String description,Long id);
    CardEntity getById(Long id);
}
