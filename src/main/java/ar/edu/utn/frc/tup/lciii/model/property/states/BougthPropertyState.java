package ar.edu.utn.frc.tup.lciii.model.property.states;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;

public class BougthPropertyState implements State {
    @Override
    public void nextState(AbstractProperty property) {
        property.setState(new FreePropertyState());
    }

    @Override
    public void executeState(PlayerImplement player, AbstractProperty property) {
        if (player.getPlayerID().equals(property.getOwner().getPlayerID())) {
            LetterByLetterPrinter.println("La propiedad es de "+player.getPlayerName()
                    +".No paga nada.");
            return;
        }
        LetterByLetterPrinter.println(player.getPlayerName()+ " debe pagar "+ property.calculateRent() +
                " de alquiler a " +property.getOwner().getPlayerName());
        player.updateBalance(-property.calculateRent());
        property.getOwner().updateBalance(property.calculateRent());



    }
}
