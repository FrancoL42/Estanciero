package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StartingSquare extends AbstractSquare {

    @Override
    public void executeSquare(PlayerImplement player) {
        LetterByLetterPrinter.println(player.getPlayerName()+" está en la casilla de salida. Recibe $5000");
        player.updateBalance(5000);

    }
}
