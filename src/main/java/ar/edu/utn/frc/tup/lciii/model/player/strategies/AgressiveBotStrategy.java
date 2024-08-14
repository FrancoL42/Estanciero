package ar.edu.utn.frc.tup.lciii.model.player.strategies;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.square.PropertySquare;

import java.util.List;

public class AgressiveBotStrategy extends AbstractBotStrategy{


     public AgressiveBotStrategy(){
        this.PREFERRED_PROVINCES = List.of("Buenos Aires","Córdoba", "Tucumán");
     }

    @Override
    public void buyProperty(PlayerImplement player,Property property) {
        AbstractProperty p = (AbstractProperty) property;
        if(p.getState() instanceof FreePropertyState){
            //Compra trenes y compañias.
            if(!(p instanceof FieldProperty))
                buy(player, p);
            if (p instanceof FieldProperty && shouldBuyProvince(p,player)) {
                buy(player, p);
            }
        }

    }



    private boolean shouldBuyProvince(Property fieldProperty, PlayerImplement player) {
        boolean shouldBuy = false;
        if (PREFERRED_PROVINCES.contains(((FieldProperty) fieldProperty).getProvince())) {
                shouldBuy = true;
        }else if(validateOwnsAllZonesInPreferredProvinces(player)){
            shouldBuy = true;
        }else if(validatePreferredZonesBoughtByOthers(player))
            shouldBuy = true;
        return shouldBuy;
    }

    private boolean validatePreferredZonesBoughtByOthers(PlayerImplement player) {
        List<AbstractProperty> listToCheck = Board.getInstance().getProperties().values().stream()
                .filter(p->(p instanceof FieldProperty)).toList();
        int provinceCounter = 0; //Contador de provincias preferidas con zonas de dueños != player
        for(Property p: listToCheck)
            for(String province: PREFERRED_PROVINCES )
                if(((FieldProperty)p).getProvince().equals(province) && player != ((FieldProperty) p).getOwner())
                    provinceCounter++;

        return provinceCounter == 3; //true si las 3 provincias preferidas tienen otro owner.
    }

    private boolean validateOwnsAllZonesInPreferredProvinces(PlayerImplement player) {
        List<FieldProperty> listFields =getListProperties(player, FieldProperty.class);
        for(FieldProperty p : listFields)
            if(!p.checkOwnsAllZonesInProvince())
                return false;

        return true;
    }

    @Override
    public void buyFarm(PlayerImplement player) {
        super.buyFarm(player,1);
    }

    @Override
    public Boolean restYesNo(PlayerImplement p) {
        return false;
    }


}
