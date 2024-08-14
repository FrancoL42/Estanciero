package ar.edu.utn.frc.tup.lciii.entity.converts;

import ar.edu.utn.frc.tup.lciii.entity.GamePropertyEntity;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.RailroadProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;


public class PropertyConvert {


    public static AbstractProperty ConvertToAbstractProperty(GamePropertyEntity gameProperty) {
        Long typePropertyId = gameProperty.getProperty().getPropertyType().getId();
        AbstractProperty abstractProperty= null;
        if(typePropertyId == 1L){
            Integer valueFarm = gameProperty.getProperty().getFarmBuyValue();
            Integer cantFarm = gameProperty.getFarmCount();

            abstractProperty = FieldProperty.builder()
                    .province(gameProperty.getProperty().getProvince().getProvince())
                    .zone(gameProperty.getProperty().getZone().getZone())
                    //.buildFarm(gameProperty.getFarmCount()>0)
                    .farmCount(cantFarm)
                    .cantPropertiesZones(gameProperty.getProperty().getNumberOfZonesInProvince())
                    .farmValue(valueFarm)
                    .build();

            abstractProperty.setName(gameProperty.getProperty().getProvince().getProvince() + " " + gameProperty.getProperty().getZone().getZone());

        } else if(typePropertyId ==2L){
            abstractProperty = new CompanyProperty();
            abstractProperty.setName(gameProperty.getProperty().getDescription());

        } else {
            abstractProperty = new RailroadProperty();
            abstractProperty.setName(gameProperty.getProperty().getDescription());
        }
        if(gameProperty.getIsMortgaged()==true){
            abstractProperty.setState(new MortgagePropertyState());
        }else if (gameProperty.getPlayer() != null){
            abstractProperty.setState(new BougthPropertyState());
        } else {
            abstractProperty.setState(new FreePropertyState());

        }

        abstractProperty.setPropertyId(gameProperty.getId());
        abstractProperty.setPropertyTypeId(typePropertyId);
        abstractProperty.setRentValue(gameProperty.getProperty().getRentValue());
        abstractProperty.setPropertyValue(gameProperty.getProperty().getBuyValue());
        abstractProperty.setIdEntityPropertyConfig(gameProperty.getProperty().getId());
        return abstractProperty;

    }

    public static GamePropertyEntity ConvertToGamePropertyEntity(AbstractProperty abstractProperty) {
        GamePropertyEntity gameProperty = new GamePropertyEntity();
        gameProperty.setId(abstractProperty.getPropertyId());

        if (abstractProperty instanceof FieldProperty) {
            FieldProperty fieldProperty = (FieldProperty) abstractProperty;
            gameProperty.setIsMortgaged(isMortgage(fieldProperty.getState()));
            gameProperty.setFarmCount(((FieldProperty) abstractProperty).getFarmCount());;

        } else if (abstractProperty instanceof RailroadProperty) {
            RailroadProperty railroadProperty = (RailroadProperty) abstractProperty;
            gameProperty.setIsMortgaged(isMortgage(railroadProperty.getState()));

        } else if (abstractProperty instanceof CompanyProperty) {
            CompanyProperty companyProperty = (CompanyProperty) abstractProperty;
            gameProperty.setIsMortgaged(isMortgage(companyProperty.getState()));

        }

        if (abstractProperty.getOwner() != null){
            gameProperty.setPlayer(PlayerConvert.ConvertToPlayerEntity(abstractProperty.getOwner()));
        }

        return gameProperty;
    }

    private static boolean isMortgage(State state){
        if(state instanceof MortgagePropertyState){
            return true;
        }
        return false;
    }
}
