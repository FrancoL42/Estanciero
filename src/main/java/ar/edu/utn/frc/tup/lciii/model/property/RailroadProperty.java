package ar.edu.utn.frc.tup.lciii.model.property;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class RailroadProperty extends AbstractProperty {


    @Override
    public void executeState(PlayerImplement player) {
        this.state.executeState(player,this);

    }

    @Override
    public void setState(State state) {
        this.state = state;

    }

    /**
     * Calcular la renta de acuerdo a las propiedades rentValue
     * y la lista de propiedades que es dueña el player
     */
    @Override
    public Integer calculateRent() {
        Integer rentPerRailroad = this.getRentValue();
        Integer multiplier = 0;
        for(Property p : this.getOwner().getProperties())
            if(p instanceof RailroadProperty)
               multiplier++;


        return rentPerRailroad*multiplier;

    }
}
