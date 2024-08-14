package ar.edu.utn.frc.tup.lciii.model.player.strategy;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.card.OutOfJailCard;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import static org.junit.jupiter.api.Assertions.*;

import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.property.RailroadProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class AbstractStrategyTest {
    private List<Card> cards;
    private final PrintStream systemOut = System.out;
    private ByteArrayOutputStream testOut;
    private AbstractProperty property;
    private PlayerImplement playerImplement;
    private List<Property> properties;

    @Mock
    private PlayerImplement playerMock;
    private ConservativeBotStrategy playerStrategy ;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        playerStrategy = new ConservativeBotStrategy();

    }

    @BeforeEach
    public void loadListProperties(){
        List<Property> list = new ArrayList<>();
        properties = new ArrayList<>();
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        playerImplement.setPlayerName("conservador");

        State state = new BougthPropertyState();
        AbstractProperty property1 = FieldProperty.builder().farmCount(5).farmValue(500).cantPropertiesZones(3).province("Formosa").zone("sur").build();
        property1.setOwner(playerImplement);
        property1.setState(state);
        property1.setName("Formosa sur");
        property1.setPropertyValue(1000);
        AbstractProperty property2 = FieldProperty.builder().farmCount(2).farmValue(200).cantPropertiesZones(3).province("Formosa").zone("centro").build();
        property2.setOwner(playerImplement);
        property2.setState(state);
        property2.setName("Formosa centro");
        property2.setPropertyValue(1000);

        AbstractProperty property3 =  FieldProperty.builder().farmCount(3).farmValue(450).cantPropertiesZones(3).province("Formosa").zone("norte").build();

        property3.setOwner(playerImplement);
        property3.setState(state);
        property3.setName("Formosa norte");
        property3.setPropertyValue(1000);

        AbstractProperty property4 = FieldProperty.builder().farmCount(0).farmValue(100).cantPropertiesZones(2).province("Cordoba").zone("norte").build();

        property4.setOwner(playerImplement);
        property4.setState(state);
        property4.setName("Cordoba norte");
        property4.setPropertyValue(1000);

        AbstractProperty property5 =  FieldProperty.builder().farmCount(0).farmValue(500).cantPropertiesZones(2).province("Buenos Aires").zone("sur").build();

        property5.setOwner(playerImplement);
        property5.setState(state);
        property5.setName("Buenos Aires centro");
        property5.setPropertyValue(1000);

        AbstractProperty property6 = FieldProperty.builder().farmCount(0).farmValue(500).cantPropertiesZones(2).province("Cordoba").zone("sur").build();

        property6.setOwner(playerImplement);
        property6.setState(state);
        property6.setName("Cordoba sur");
        property6.setPropertyValue(1000);

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
        properties.add(railProperty);
        playerImplement.setProperties(properties);
        playerImplement.setStrategy(playerStrategy);
    }

    @BeforeEach
    public void loadListCards(){
        cards = new ArrayList<>();
        AbstractCard card = new OutOfJailCard();
        card.setOrder(1);
        card.setCardDescription("Salir de la carcel");
        card.setCardType(1L);
        AbstractCard card2 = new OutOfJailCard();
        card2.setOrder(2);
        card2.setCardDescription("Salir de la carcel");
        card2.setCardType(2L);
        cards.add(card);
        cards.add(card2);
        playerImplement.setCards(cards);
    }
    @AfterEach
    public void clearList(){
        properties = new ArrayList<>();
    }
    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setOut(systemOut);


    }

    @Test
    public void useCardTest(){
        playerImplement.setStrategy(playerStrategy);
        playerImplement.useCard();
        assertEquals(1,playerImplement.getCards().size() );
        playerImplement.useCard();
        playerImplement.useCard();
        assertTrue(playerImplement.getCards().isEmpty());
    }

    @Test
    public void sellFarmTest(){
        playerImplement.setBalance(-9000);
        playerImplement.setStrategy(playerStrategy);
        playerImplement.sellFarm();

        assertEquals(-4750, playerImplement.getBalance());
        List<Property> fieldProperties = playerImplement.getProperties().stream().filter(p-> p instanceof  FieldProperty).filter(p->((FieldProperty) p).getFarmCount()>0).toList();
        assertTrue(fieldProperties.isEmpty());
    }

    @Test
    public void buyFarm(){

        playerImplement.setStrategy(playerStrategy);
        playerImplement.buyFarm();

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("conservador compra chacra en Formosa centro", output[0]);
        assertEquals("conservador compra chacra en Formosa norte", output[1]);
        assertEquals("conservador compra chacra en Cordoba norte", output[2]);
        assertEquals("conservador compra chacra en Cordoba sur", output[3]);
        assertEquals(4750, playerImplement.getBalance());



    }

    @Test
    @Disabled

    public void validateCardsTest(){
        when(playerMock.getCards()).thenReturn(cards);
        when(playerMock.hasAnyFarm()).thenReturn(false);
        when(playerMock.getProperties()).thenReturn(new ArrayList<>());
        when(playerMock.areAllPropertiesMortgaged()).thenReturn(true);
        when(playerMock.getBalance()).thenReturn(-1);
        playerStrategy.validateBalance(properties,playerMock);

        verify(playerMock, times(7)).getCards();
        verify(playerMock, never()).hasAnyFarm();
        verify(playerMock, never()).getProperties();
        verify(playerMock, never()).areAllPropertiesMortgaged();
        verify(playerMock, never()).setLoser(true);
    }


    @Test
    public void sellPropertyTest(){
        playerImplement.setStrategy(playerStrategy);
        playerImplement.setBalance(-1000);
        playerImplement.sellProperty();
        assertEquals(0,playerImplement.getBalance());

    }
    @Test
    public void payOffMortgagesTest(){
        playerImplement.setStrategy(playerStrategy);
        AbstractProperty propertyMortgage = new RailroadProperty();
        propertyMortgage.setName("Tren Roca");
        propertyMortgage.setOwner(playerImplement);
        propertyMortgage.setState(new MortgagePropertyState());
        propertyMortgage.setPropertyValue(1500);
        playerImplement.getProperties().add(propertyMortgage);
        playerStrategy.payOffMortgages(playerImplement);
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("conservador levanta la hipoteca de Tren Roca", output[0]);
        assertEquals(5250, playerImplement.getBalance());

    }
    @Test
    @Disabled
    public void validateTest(){

        when(playerMock.getCards()).thenReturn(new ArrayList<>());
        when(playerMock.hasAnyFarm()).thenReturn(true);
        when(playerMock.getProperties()).thenReturn(new ArrayList<>());
        when(playerMock.areAllPropertiesMortgaged()).thenReturn(true);
        when(playerMock.getBalance()).thenReturn(-1);
        playerStrategy.validateBalance(properties,playerMock);

        verify(playerMock, times(4)).getCards();
        verify(playerMock, never()).hasAnyFarm();
        verify(playerMock, never()).getProperties();
        verify(playerMock, never()).areAllPropertiesMortgaged();
        verify(playerMock, never()).setLoser(true);
    }

    @Test
    public void tesLooser(){
        AgressiveBotStrategy strategy = new AgressiveBotStrategy();
        playerImplement.setStrategy(strategy);
        playerImplement.updateBalance(-100000);
        assertEquals(-80800,playerImplement.getBalance());
        assertTrue(playerImplement.isLoser());
    }

}
