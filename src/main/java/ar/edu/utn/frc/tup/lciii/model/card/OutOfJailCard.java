package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OutOfJailCard extends AbstractCard {
    Console console = new Console();

    @Override
    public void executeCard(PlayerImplement p) {
        LetterByLetterPrinter.println(this.cardDescription);

        p.setCardToList(this);
    }
}
