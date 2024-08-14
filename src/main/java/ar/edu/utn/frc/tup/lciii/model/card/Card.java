package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;

public interface Card {
     void executeCard(PlayerImplement playerImplement);
     void addCardToBoard();
}
