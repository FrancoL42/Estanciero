package ar.edu.utn.frc.tup.lciii.model.player;

import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.card.Card;

public interface Player {
    void useCard();
    void buyProperty(Property property);
    void mortgageProperty();
    void buyFarm();
    void sellFarm();

  //  Boolean isRestTurnOption();
    void calculateSteps(Integer steps);

    void setCardToList(Card card);

    void updateBalance(Integer amount);

    void sellCard();
    Boolean areAllPropertiesMortgaged();
    Boolean hasAnyFarm();
    Boolean restYesOrNo();
    boolean decide();
    void sellProperty();
}
