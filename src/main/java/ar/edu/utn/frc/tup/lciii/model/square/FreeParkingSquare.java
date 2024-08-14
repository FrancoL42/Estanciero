package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;
import lombok.*;

import java.util.LinkedHashMap;
@Getter
@Setter
@NoArgsConstructor
public class FreeParkingSquare extends AbstractSquare {

    @Override
    public void executeSquare(PlayerImplement player) {
        LetterByLetterPrinter.println(player.getPlayerName()+
                " llegó al estacionamiento, no puede quedarse! Se tiran los dados nuevamente!.");
    }


}
