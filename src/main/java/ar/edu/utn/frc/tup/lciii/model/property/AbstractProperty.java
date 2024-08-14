package ar.edu.utn.frc.tup.lciii.model.property;

import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import lombok.*;
import org.hibernate.cache.internal.SimpleCacheKeysFactory;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class AbstractProperty implements Property {
    protected Long idEntityPropertyConfig;
    protected Long propertyId;
    protected State state;
    protected int propertyValue;
    protected int rentValue;
    protected PlayerImplement owner;
    protected Long propertyTypeId;
    protected String name;

    @Override
    public String toString(){
        return name;
    }

    @Override
    public void nextState(){state.nextState(this);}

}
