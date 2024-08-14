package ar.edu.utn.frc.tup.lciii.service;


import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;

import java.util.List;

public interface CardService {
    List<Card> getAllChanceCards();
    List<Card> getAllDestinyCards();

    List<AbstractCard> getAllChanceCardsByGame(Long gameId, List<PlayerImplement> players);
    List<AbstractCard> getAllDestinyCardsByGame(Long gameId, List<PlayerImplement> players);
}
