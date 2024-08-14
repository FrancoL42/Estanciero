package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import lombok.Getter;
import lombok.Setter;

import javax.swing.text.Position;
@Getter
@Setter
public class DisplacementCard extends AbstractCard {
    private Integer displacement;
    @Override
    public void executeCard(PlayerImplement player) {
        LetterByLetterPrinter.println(this.cardDescription);
        player.calculateSteps(displacement);
        LetterByLetterPrinter.println(player.getPlayerName()+
                " se mueve a la casilla "+player.getSquare().toString());
        addCardToBoard();
        player.executeSquare();
    }
}
