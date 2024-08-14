package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Random;
@Getter
@Setter
@NoArgsConstructor
public class LuckSquare extends AbstractSquare {

    CardService cardService = new CardServiceImpl();


    @Override
    public void executeSquare(PlayerImplement player){
        LetterByLetterPrinter.println(player.getPlayerName()+
                " ha llegado a una casilla de suerte.Saca una carta de suerte.");
        Card card =  Board.getInstance().getChanceCard();
        LetterByLetterPrinter.println("Obtuvo esta carta de Suerte:");
        card.executeCard(player);

    }

}
