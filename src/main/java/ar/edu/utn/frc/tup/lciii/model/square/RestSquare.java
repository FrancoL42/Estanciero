package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.LinkedHashMap;
@Getter
@Setter
@NoArgsConstructor
public class RestSquare extends AbstractSquare {
    //TODO: revisar porque esta commiteado
    @Override
    public void executeSquare(PlayerImplement player) {

        if(player.getRestTurnCounter() == 0)
            LetterByLetterPrinter.println(player.getPlayerName()+ " ha llegado a la casilla de descanso!");
//        if (player.getRestTurnCounter() < 2) {
//            if(player.restYesOrNo())
//                return;
//        } else {
//            LetterByLetterPrinter.println(player.getPlayerName()
//                    +" ha descansado el maximo de turnos permitidos. Continua tu turno.");
//        }
//        player.setRestTurnCounter(0);



    }


}
