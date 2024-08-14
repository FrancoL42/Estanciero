package ar.edu.utn.frc.tup.lciii.model.property;

import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;

public interface Property {
    void executeState(PlayerImplement player);

    void setState(State state);

    Integer calculateRent();

    void nextState();
}
