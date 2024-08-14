package ar.edu.utn.frc.tup.lciii.model.player.strategies;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.square.PropertySquare;

import java.util.Comparator;
import java.util.List;

public class ConservativeBotStrategy extends AbstractBotStrategy {


    public ConservativeBotStrategy(){
        this.PREFERRED_PROVINCES = List.of("Formosa", "Rio Negro", "Salta");
    }

    @Override
    public void buyProperty(PlayerImplement player,Property p) {
        //Compra campos unicamente, NO TRENES NI EMPRESAS
        AbstractProperty property = (AbstractProperty) p;
        if (property.getState() instanceof FreePropertyState)
            if (property instanceof FieldProperty && shouldBuyProvince(property)) {
                buy(player,property);
            }

    }

    private boolean shouldBuyProvince(Property fieldProperty) {
        boolean shouldBuy = false;
        if (PREFERRED_PROVINCES.contains(((FieldProperty) fieldProperty).getProvince())) {
            if (preferredFieldsBought % 5 != 4)
                shouldBuy = true;
        } else if( preferredFieldsBought % 5 == 4)
            shouldBuy = true;

        return shouldBuy;
    }

    @Override
    public void buyFarm(PlayerImplement player) {
        super.buyFarm(player,0.3);
    }

    @Override
    public Boolean restYesNo(PlayerImplement p) {
        p.setRestTurnCounter(p.getRestTurnCounter()+1);
        return true;
    }

    @Override
    public void goToJail(PlayerImplement player) {

    }
}
