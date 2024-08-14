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
public class PrizePenaltySquare extends AbstractSquare {
    private Integer prizePenaltyAmount;

    @Override
    public void executeSquare(PlayerImplement player) {
        switch (numberSquare){
        case 4->LetterByLetterPrinter.println(player.getPlayerName()+" debe pagar el impuesto a los reditos de 5000!");
        case 7->LetterByLetterPrinter.println(player.getPlayerName()+" ganó el premio ganadero.Gana 2500.");
        case 41->LetterByLetterPrinter.println(player.getPlayerName()+" debe pagar el impuesto a las ventas de 2000!");
        }
        player.updateBalance(prizePenaltyAmount);
    }



}
