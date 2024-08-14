package ar.edu.utn.frc.tup.lciii.model.player.strategy;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.RailroadProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.*;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.when;

public class AgressivePlayerStrategyTest {
    private AgressiveBotStrategy playerStrategy ;

    private PlayerImplement playerImplement;

    private AbstractProperty property;

    private List<Card> card;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        playerStrategy = new AgressiveBotStrategy();
        card = Mockito.mock(List.class);

    }
    @Test
    public void TestBuyProperty(){
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        property = new RailroadProperty();
        property.setState(new FreePropertyState());
        property.setPropertyValue(1000);
        playerStrategy.buyProperty(playerImplement,property);
        assertSame(playerImplement, property.getOwner());
        assertEquals(playerImplement.getBalance(),5000);;
    }

    @Test
    public void fieldProvinceBuy(){
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        property =  FieldProperty.builder().province("Buenos Aires").zone("Norte").build();
        property.setPropertyValue(2500);
        property.setState(new FreePropertyState());

        playerStrategy.buyProperty(playerImplement,property);
        assertSame(playerImplement, property.getOwner());
        assertEquals(playerImplement.getBalance(),3500);;
    }

    @Test
    public void TestBuyFarm(){
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        List<Property> properties = new ArrayList<>();
        FieldProperty saltaNorte = FieldProperty.builder().province("Salta").zone("Norte").cantPropertiesZones(2).farmValue(500).build();
        FieldProperty saltaSur = FieldProperty.builder().province("Salta").zone("Sur").cantPropertiesZones(2).farmValue(500).build();
        RailroadProperty railroadProperty = new RailroadProperty();
        saltaSur.setOwner(playerImplement);
        saltaNorte.setOwner(playerImplement);
        properties.add(saltaNorte);
        properties.add(saltaSur);
        properties.add(railroadProperty);
        playerImplement.setProperties(properties);
        playerStrategy.buyFarm(playerImplement);
        assertEquals(saltaNorte.getFarmCount(),1);
        assertEquals(saltaSur.getFarmCount(),1);
   }

    @Test
    public void TestMortGageProperty(){
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(-600);
        List<Property> properties = new ArrayList<>();
        FieldProperty saltaNorte = FieldProperty.builder().province("Salta").zone("Norte").cantPropertiesZones(2).farmValue(500).build();
        saltaNorte.setPropertyValue(750);
        saltaNorte.setState(new BougthPropertyState());
        FieldProperty saltaSur = FieldProperty.builder().province("Salta").zone("Sur").cantPropertiesZones(2).farmValue(500).build();
        saltaSur.setPropertyValue(700);
        saltaSur.setState(new BougthPropertyState());
        RailroadProperty railroadProperty = new RailroadProperty();
        railroadProperty.setPropertyValue(1000);
        railroadProperty.setState(new BougthPropertyState());
        saltaSur.setOwner(playerImplement);
        saltaNorte.setOwner(playerImplement);
        properties.add(saltaNorte);
        properties.add(saltaSur);
        properties.add(railroadProperty);
        playerImplement.setProperties(properties);
        playerStrategy.mortgageProperty(playerImplement);
        assertEquals(-150, playerImplement.getBalance());
        Optional<AbstractProperty>  mortgageProperty = playerImplement.getProperties().stream().map(p->(AbstractProperty) p).filter(p->p.getState() instanceof MortgagePropertyState).findFirst();
        assertTrue(mortgageProperty.isPresent());
    }
//    @Test
//    public void TestUseCard(){
//        playerStrategy.useCard(card);
//        when(card.get(0)).thenThrow(new IndexOutOfBoundsException());
//    }
//    @Test
//    public void TestSellFarm(){
//        playerStrategy.sellFarm();
//    }

    @Test
    public void validatePropertiesTest(){
        playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("pepe");
        playerImplement.setBalance(4000);
        AbstractProperty property1 = FieldProperty.builder().farmCount(0).farmValue(500).cantPropertiesZones(3).province("Salta").zone("sur").build();
        property1.setState(new FreePropertyState());
        property1.setName("Salta sur");
        property1.setPropertyValue(1000);
        property1.setPropertyId(1L);
        playerStrategy.buyProperty(playerImplement,property1);

    }

    private void boardLoad(){

        List<AbstractProperty> properties = new ArrayList<>();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        State state = new BougthPropertyState();
        AbstractProperty property1 = FieldProperty.builder().farmCount(5).farmValue(500).cantPropertiesZones(3).province("Formosa").zone("sur").build();
        property1.setOwner(playerImplement);
        property1.setState(state);
        property1.setName("Formosa sur");
        property1.setPropertyValue(1000);
        property1.setPropertyId(1L);
        AbstractProperty property2 = FieldProperty.builder().farmCount(2).farmValue(200).cantPropertiesZones(3).province("Formosa").zone("centro").build();
        property2.setOwner(playerImplement);
        property2.setState(state);
        property2.setName("Formosa centro");
        property2.setPropertyValue(1000);
        property2.setPropertyId(2L);

        AbstractProperty property3 =  FieldProperty.builder().farmCount(3).farmValue(450).cantPropertiesZones(3).province("Formosa").zone("norte").build();

        property3.setOwner(null);
        property3.setState(state);
        property3.setName("Formosa norte");
        property3.setPropertyValue(1000);
        property3.setPropertyId(3L);

        AbstractProperty property4 = FieldProperty.builder().farmCount(0).farmValue(100).cantPropertiesZones(2).province("Cordoba").zone("norte").build();

        property4.setOwner(null);
        property4.setState(state);
        property4.setName("Cordoba norte");
        property4.setPropertyValue(1000);
        property4.setPropertyId(4L);

        AbstractProperty property5 =  FieldProperty.builder().farmCount(5).farmValue(500).cantPropertiesZones(2).province("Buenos Aires").zone("sur").build();

        property5.setOwner(playerImplement);
        property5.setState(state);
        property5.setName("Buenos Aires centro");
        property5.setPropertyValue(1000);
        property5.setPropertyId(5L);

        AbstractProperty property6 = FieldProperty.builder().farmCount(2).farmValue(500).cantPropertiesZones(2).province("Cordoba").zone("sur").build();

        property6.setOwner(playerImplement);
        property6.setState(state);
        property6.setName("Cordoba sur");
        property6.setPropertyValue(1000);
        property6.setPropertyId(6L);

        properties.add(property1);
        properties.add(property2);
        properties.add(property3);
        properties.add(property4);
        properties.add(property5);
        properties.add(property6);

        AbstractProperty railProperty = new RailroadProperty();
        railProperty.setState(state);
        railProperty.setPropertyValue(1500);
        railProperty.setOwner(playerImplement);
        railProperty.setName("Tren Sarmiento");
        railProperty.setPropertyId(7L);

        properties.add(railProperty);
        Map<Integer, AbstractProperty> list = new HashMap<>();
        properties.forEach(p->{
            list.put(p.getPropertyId().intValue(),p);
        });
        Board.getInstance().setProperties(list);
        playerImplement.setStrategy(playerStrategy);
    }
}
