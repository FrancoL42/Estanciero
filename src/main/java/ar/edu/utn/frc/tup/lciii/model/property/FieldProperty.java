package ar.edu.utn.frc.tup.lciii.model.property;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;
import lombok.*;

@Getter
@Setter
@Builder
public class FieldProperty extends AbstractProperty {

    private String province;
    //Norte, centro, sur
    private String zone;
    private Integer cantPropertiesZones;
    private int farmValue;
    private int farmCount;



    @Override
    public void executeState(PlayerImplement player) {

        state.executeState(player, this);

    }

    @Override
    public void setState(State state) {
        this.state = state;

    }
    public String getName(){
        return toString();
    }
    @Override
    public String toString(){
        return province+" "+zone;
    }

    @Override
    public Integer calculateRent() {
        //En principio, alquiler es el que figura para el campo.
        Integer rent = this.rentValue;
        //Si tiene todas la de la zona, alquiler se duplica
        if (checkOwnsAllZonesInProvince())
            if (farmCount == 0)
                rent *= 2;
        //Formula aproximada segun cant Chacras, según valores del juego original.
        switch (farmCount) {
            case 1 -> rent = this.rentValue * 5;
            case 2 -> rent = this.rentValue * 15;
            case 3 -> rent = this.rentValue * 40;
            case 4 -> rent = this.rentValue * 50;
            case 5 -> rent = this.rentValue * 70; // 5 chacras = 1 estancia.
        }


        //  LetterByLetterPrinter.println("ejecutando..");
        return rent;
    }

    public boolean checkOwnsAllZonesInProvince() {
        int sameZonePropertiesCounter = 0;
        for (Property p : this.getOwner().getProperties()) {
            if (p instanceof FieldProperty) {
                if (((FieldProperty) p).getProvince().equals(this.province)) {
                    sameZonePropertiesCounter++;
                }
            }
        }
        return sameZonePropertiesCounter == cantPropertiesZones;
    }


}
