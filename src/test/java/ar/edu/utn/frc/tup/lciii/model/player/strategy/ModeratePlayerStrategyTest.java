package ar.edu.utn.frc.tup.lciii.model.player.strategy;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.card.OutOfJailCard;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.RailroadProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class ModeratePlayerStrategyTest {
    private ModerateBotStrategy playerStrategy ;
    private PlayerImplement playerImplement;

    private AbstractProperty property;
    private List<Card> card;

    @BeforeEach
    public void setUp(){
        playerStrategy = new ModerateBotStrategy();
        property = FieldProperty.builder()
                .province("Mendoza")
                .farmValue(700)
                .farmCount(0)
                .build();
        property.setPropertyValue(700);
        card = Mockito.mock(List.class);

    }
    @Test
    public void TestBuyRailRoadProperty(){
        playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco"); //para testear los msj
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        AbstractProperty property1 = new RailroadProperty();
        property1.setName("Coca cola company");
        property1.setState(new FreePropertyState());
        property1.setPropertyValue(700);
        playerStrategy.buyProperty(playerImplement, property1);
        assertEquals(playerImplement.getBalance(),5300);;
    }
    @Test
    public void TestBuyFieldPropertyTrue(){
        playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco"); //para testear los msj
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        property.setState(new FreePropertyState());
        playerStrategy.buyProperty(playerImplement, property);
        assertEquals(playerImplement.getBalance(),5300);;
    }
    @Test
    public void TestBuyFieldPropertyFalse(){
        playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco"); //para testear los msj
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        Property property1 = FieldProperty.builder()
                .province("Misiones")
                .farmValue(700)
                .farmCount(0)
                .build();
        property1.setState(new FreePropertyState());
        playerStrategy.buyProperty(playerImplement, property1);
        assertEquals(playerImplement.getBalance(),6000);;
    }
    @Test
    public void TestRest(){
        playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco"); //para testear los msj
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        Boolean respuesta= playerStrategy.restYesNo(playerImplement);
        assertNotNull(respuesta);
    }
    @Disabled
    @Test
    public void TestIsLoser(){
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(50000);
        OutOfJailCard c = new OutOfJailCard();
        OutOfJailCard c2 = new OutOfJailCard();
        c.setCardType(1L);
        c.setCardType(2L);
        List<Card> cards = List.of(c,c2);
        ModerateBotStrategy ms = new ModerateBotStrategy();
        playerImplement.setStrategy(ms);
        playerImplement.setCards(cards);
        playerImplement.updateBalance(-100000);
    }

}
