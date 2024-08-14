package ar.edu.utn.frc.tup.lciii.model.property.states;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;

public class MortgagePropertyState implements State{
    @Override
    public void nextState(AbstractProperty property) {
            property.setState(new BougthPropertyState());

    }

    @Override
    public void executeState(PlayerImplement player, AbstractProperty property) {
        //El Banco se cobra el 10% de la hipoteca como interes.
        if(player.equals(property.getOwner()))
           LetterByLetterPrinter.println(player.getPlayerName()+" cayó en su propiedad hipotecada.");
        else
            LetterByLetterPrinter.println(player.getPlayerName()+ " cayó en " +property.getName()
               + " que esta hipotecada.No paga alquiler.");

        int value = property.getPropertyValue()/2*9/10;
        player.updateBalance(value);

    }

}
