package ar.edu.utn.frc.tup.lciii.model.player.strategies;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.Property;

import java.util.List;

public interface Strategy {

    void buyProperty(PlayerImplement player,Property property);
    void mortgageProperty( PlayerImplement player);
    boolean useCard(PlayerImplement player);
    void buyFarm( PlayerImplement playerImplement);
    void sellFarm(PlayerImplement playerImplement);
    void validateBalance(List<Property> properties, PlayerImplement playerImplement);
     void sellCard( PlayerImplement p);
     void sellProperty(PlayerImplement p);
     Boolean restYesNo(PlayerImplement p);
    void payOffMortgages(PlayerImplement p);
    void goToJail(PlayerImplement player);

    boolean decide(PlayerImplement player);
}
