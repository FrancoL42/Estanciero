package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.square.DestinySquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GoToSquareCard extends AbstractCard{
    private Integer numberSquare;
    @Override

    public void executeCard(PlayerImplement p) {
        LetterByLetterPrinter.println(this.cardDescription);
        p.setSquare(Board.getInstance().getSquare(numberSquare));
        addCardToBoard();
        p.executeSquare();
    }



}
