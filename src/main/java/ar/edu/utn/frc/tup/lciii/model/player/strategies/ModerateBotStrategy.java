package ar.edu.utn.frc.tup.lciii.model.player.strategies;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.RailroadProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.square.PropertySquare;

import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class ModerateBotStrategy extends AbstractBotStrategy {

    public ModerateBotStrategy(){
        this.PREFERRED_PROVINCES = List.of("Mendoza", "Santa Fe", "Tucumán");
    }

    @Override
    public void buyProperty(PlayerImplement player,Property p) {
        AbstractProperty property = (AbstractProperty) p;
        if (property.getState() instanceof FreePropertyState) {
            if (property instanceof RailroadProperty)
                buy(player,property);
            if (property instanceof FieldProperty && shouldBuyProvince(property)) {
                buy(player,property);
            }
        }
    }

    private boolean shouldBuyProvince(Property fieldProperty) {
        boolean shouldBuy = false;
        if (PREFERRED_PROVINCES.contains(((FieldProperty) fieldProperty).getProvince())) {
            if (preferredFieldsBought % 3 != 2)
                shouldBuy = true;
        } else if (preferredFieldsBought % 3 == 2)
            shouldBuy = true;

        return shouldBuy;
    }

    @Override
    public void buyFarm(PlayerImplement player) {
        int allBoughtPropertiesCounter = Board.getInstance().getProperties().values().stream()
                .filter(p -> p.getOwner() != null).toList().size();
        if ((double) allBoughtPropertiesCounter / Board.getInstance().getProperties().size() >= 0.75)
            super.buyFarm(player, 1);
        else
            super.buyFarm(player, 0.5);
    }


    @Override
    public Boolean restYesNo(PlayerImplement player) {
        boolean choice = new Random().nextBoolean();
        if(choice)
            player.setRestTurnCounter(player.getRestTurnCounter()+1);

        return choice;
    }

}
