package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GoToJailSquare extends AbstractSquare {

    @Override
    public void executeSquare(PlayerImplement player) {
        if (player.isInJail()) {
            LetterByLetterPrinter.println(player.getPlayerName() + "continua en la cárcel.");
        } else {
            LetterByLetterPrinter.println(player.getPlayerName() +
                    " cayó en la casilla Marche Preso!¡Ha violado alguna ley! Entonces irá a la cárcel.");
        }

        player.goToJail();
    }

}
