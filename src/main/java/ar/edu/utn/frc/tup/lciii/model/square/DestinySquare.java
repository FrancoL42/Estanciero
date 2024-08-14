package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import ar.edu.utn.frc.tup.lciii.service.SquareService;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.SquareServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DestinySquare extends AbstractSquare {

    SquareService squareService = new SquareServiceImpl();
    CardService cardService = new CardServiceImpl();

    @Override
    public void executeSquare(PlayerImplement player) {
        LetterByLetterPrinter.println(player.getPlayerName() +
                " ha llegado a una casilla de destino.Saca una carta de destino.");
        Card card = Board.getInstance().getdestinyCard();
        LetterByLetterPrinter.println("Obtuvo esta carta de Destino:");
        card.executeCard(player);

    }
}
