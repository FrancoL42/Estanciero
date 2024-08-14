package ar.edu.utn.frc.tup.lciii.model.property.states;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



public class FreePropertyState implements State {
    @Override
    public void nextState(AbstractProperty property) {
        property.setState(new BougthPropertyState());

    }


    @Override
    public void executeState(PlayerImplement player, AbstractProperty property) {
        player.buyProperty(property);
    }
}
