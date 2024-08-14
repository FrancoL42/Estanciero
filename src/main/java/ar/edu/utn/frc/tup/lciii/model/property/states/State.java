package ar.edu.utn.frc.tup.lciii.model.property.states;

import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;

public interface State {

    void nextState(AbstractProperty property);

    void executeState(PlayerImplement player, AbstractProperty property);
}
