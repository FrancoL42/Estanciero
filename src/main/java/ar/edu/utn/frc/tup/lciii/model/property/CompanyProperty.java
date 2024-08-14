package ar.edu.utn.frc.tup.lciii.model.property;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceImpl;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;



public class CompanyProperty extends AbstractProperty{

    @Override
    public void executeState(PlayerImplement player) {
        state.executeState(player,this);

    }

    @Override
    public void setState(State state) {
      this.state = state;

    }

    @Override
    public Integer calculateRent() {
        //En principio, 100 veces los dados.
        Integer rent = 100*DiceImpl.getInstance().getResult().getSumDices();
        int companyPropertiesCounter = 0;

        //Contar cantidad de compañias en posesion
        for(Property p : this.getOwner().getProperties())
            if(p instanceof CompanyProperty)
              companyPropertiesCounter++;

        //Calcular Rent en base a la cantidad de properties.
        rent = rent*companyPropertiesCounter;


        return rent;
    }



}
