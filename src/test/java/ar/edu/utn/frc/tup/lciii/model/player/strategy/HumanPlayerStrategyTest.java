package ar.edu.utn.frc.tup.lciii.model.player.strategy;

import ar.edu.utn.frc.tup.lciii.entity.converts.PropertyConvert;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.card.OutOfJailCard;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.*;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import ar.edu.utn.frc.tup.lciii.model.property.states.State;
import com.sun.jdi.Field;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class HumanPlayerStrategyTest {
    private List<Card> cards;
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private AbstractProperty property;
    private PlayerImplement playerImplement;
    @Mock
    private List<Property> properties;

    @Mock
    private PlayerImplement playerMock;
    private HumanPlayerStrategy playerStrategy ;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        playerStrategy = new HumanPlayerStrategy();

    }

    @BeforeEach
    public void loadListProperties(){
        List<Property> list = new ArrayList<>();
        properties = new ArrayList<>();
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
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

        AbstractProperty property5 =  FieldProperty.builder().farmCount(5).farmValue(500).cantPropertiesZones(2).province("Buenos Aires").zone("sur").build();

        property5.setOwner(playerImplement);
        property5.setState(state);
        property5.setName("Buenos Aires centro");
        property5.setPropertyValue(1000);

        AbstractProperty property6 = FieldProperty.builder().farmCount(2).farmValue(500).cantPropertiesZones(2).province("Cordoba").zone("sur").build();

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
        System.setIn(systemIn);
        System.setOut(systemOut);


    }
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream((data + System.lineSeparator()).getBytes());
        System.setIn(testIn);

    }
    @Test
    public void TestBuyProperty(){
        final String testString = "s" + System.lineSeparator() ;
        provideInput(testString);

        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        playerImplement.setPlayerName("pepito");
        playerImplement.setProperties(new ArrayList<>());
        property = new RailroadProperty();
        property.setState(new FreePropertyState());
        property.setPropertyValue(1000);
        property.setName("Tren Sarmiento");
        playerStrategy  = new HumanPlayerStrategy();
        playerStrategy.buyProperty(playerImplement,property);
        assertSame(playerImplement, property.getOwner());
        assertEquals(playerImplement.getBalance(),5000);;
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("¿Desea comprar Tren Sarmiento de valor $1000? (Saldo:$6000)", output[0]);
        assertEquals("Ingrese S (si) o N (no)", output[1]);
        assertEquals("pepito compra Tren Sarmiento", output[2]);
    }
    @Test
    public void TestBuyFullFarm(){
        final String testString = "2" + System.lineSeparator()+ "1"+System.lineSeparator() ;
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerStrategy.buyFarm(playerImplement);

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Ingrese una de las siguientes opciones", output[0]);
        assertEquals("1: Formosa sur", output[1]);
        assertEquals("2: Formosa centro", output[2]);
        assertEquals("3: Formosa norte", output[3]);
        assertEquals("4: Cordoba norte", output[4]);
        assertEquals("5: Cordoba sur", output[5]);
        assertEquals("Seleccionó: Formosa centro", output[6]);
        assertEquals("¿Cuantas chacras quiere comprar?. Actualmente tiene: 2", output[7]);
        assertEquals("Ingrese un maximo de 3 incluyendo estancia.", output[8]);
    }

    @Test
    public void TestBuyFarm(){
        final String testString = "1" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerStrategy.buyFarm(playerImplement);

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Ingrese una de las siguientes opciones", output[0]);
        assertEquals("1: Formosa sur", output[1]);
        assertEquals("2: Formosa centro", output[2]);
        assertEquals("3: Formosa norte", output[3]);
        assertEquals("4: Cordoba norte", output[4]);
        assertEquals("5: Cordoba sur", output[5]);
        assertEquals("Seleccionó: Formosa sur", output[6]);
        assertEquals("Ya tiene una estancia.No puede comprar mas.", output[7]);
    }

    @Test
    public void TestMortGageProperty(){
        final String testString = "1" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerImplement.setStrategy(playerStrategy);
        playerImplement.setBalance(-100);
        playerStrategy.mortgageProperty(playerImplement);
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Ingrese una de las siguientes opciones", output[0]);
        assertEquals("1: Cordoba norte", output[1]);
        assertEquals("2: Tren Sarmiento", output[2]);
        assertEquals("Seleccionó: Cordoba norte", output[3]);


    }
    @Test
    public void TestSellFarm(){
        final String testString = "1" + System.lineSeparator()+"2"+System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerStrategy.sellFarm( playerImplement);

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Ingrese una de las siguientes opciones", output[0]);
        assertEquals("1: Formosa sur", output[1]);
        assertEquals("2: Formosa centro", output[2]);
        assertEquals("3: Formosa norte", output[3]);
        assertEquals("4: Buenos Aires sur", output[4]);
        assertEquals("5: Cordoba sur", output[5]);
        assertEquals("Seleccionó: Formosa sur", output[6]);
        assertEquals("¿Cuantas chacras quiere vender?. Actualmente tiene: 5", output[7]);
        assertEquals(3, ((FieldProperty)playerImplement.getProperties().get(0)).getFarmCount());
    }

    @Test
    public void negativeBalanceUpdateValidate(){
        final String testString = "4" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        when(playerMock.areAllPropertiesMortgaged()).thenReturn(false);
        when(playerMock.hasAnyFarm()).thenReturn(false);
        when(playerMock.getCards()).thenReturn(List.of());
        when(playerMock.getProperties()).thenReturn(properties);
        when(playerMock.getBalance()).thenReturn(-100);

        playerStrategy.validateBalance(properties, playerMock);

        verify(playerMock ).sellProperty();
    }

    @Test
    public void restYesTest(){
        final String testString = "s" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerImplement.setStrategy(playerStrategy);
        Boolean response = playerImplement.restYesOrNo();
        assertTrue(response);
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("¿Quieres descansar un turno?", output[0]);

    }

    @Test
    public void restNoTest(){
        final String testString = "n" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerImplement.setStrategy(playerStrategy);
        Boolean response = playerImplement.restYesOrNo();
        assertFalse(response);
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("¿Quieres descansar un turno?", output[0]);

    }

    @Test
    public void useCardTest(){
        final String testString = "s" + System.lineSeparator()+"1"+System.lineSeparator()+"s"+System.lineSeparator()+"1"+System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerStrategy.useCard(playerImplement);

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("¿quieres usar una carta para salir?", output[0]);
        assertEquals("Ingrese S (si) o N (no)", output[1]);
        assertEquals("Seleccione una carta a usar: ", output[2]);

        assertEquals(1,playerImplement.getCards().size());
        assertFalse(playerImplement.isInJail());
        playerStrategy.useCard(playerImplement);
        assertTrue(playerImplement.getCards().isEmpty());
    }

    @Test
    public void goToJail(){
        final String testString = "s" + System.lineSeparator()+"1"+System.lineSeparator()+"s"+System.lineSeparator()+"1"+System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerStrategy.goToJail(playerImplement);

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("¿quieres usar una carta para salir?", output[0]);
        assertEquals("Ingrese S (si) o N (no)", output[1]);
        assertEquals("Seleccione una carta a usar: ", output[2]);

    }

    @Test
    public void goToJailWithoutCards(){
        final String testString = "s"+System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerImplement.setCards(new ArrayList<>());
        playerStrategy.goToJail(playerImplement);

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("No tiene cartas para usar.", output[0]);
        assertEquals("¿quiere pagar una multa de 1000?", output[1]);
        assertEquals("Ingrese S (si) o N (no)", output[2]);
        assertEquals("Pagaste 1000 y saliste de la carcel", output[3]);
        assertEquals(5000, playerImplement.getBalance());




    }

    @Test
    public void sellCardsTest(){
        final String testString = "1" + System.lineSeparator()+"1"+System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerStrategy.sellCard(playerImplement);
        assertEquals(7000, playerImplement.getBalance());

        playerImplement.setCards(new ArrayList<>());
        playerStrategy.sellCard(playerImplement);
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("¿Que carta deseas vender?", output[0]);
        assertEquals("Ingrese una de las siguientes opciones", output[1]);
        assertEquals("1: Salir de la carcel", output[2]);
        assertEquals("2: Salir de la carcel", output[3]);
        assertEquals("Seleccionó: Salir de la carcel", output[4]);
        assertEquals("Vendes la carta.Obtienes $1000", output[5]);
        assertEquals("No tienes cartas para vender.", output[6]);

    }

    @Test
    public void payOffMortgagesTest(){
        final String testString = "1" + System.lineSeparator()+"s"+System.lineSeparator()+ "1" + System.lineSeparator()+"n" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();

        AbstractProperty property3 =  FieldProperty.builder().farmCount(0).farmValue(450).cantPropertiesZones(3).province("Salta").zone("norte").build();
        property3.setOwner(playerImplement);
        property3.setState(new MortgagePropertyState());
        property3.setName("Salta norte");
        property3.setPropertyValue(1500);

        AbstractProperty property4 =  FieldProperty.builder().farmCount(0).farmValue(450).cantPropertiesZones(3).province("Salta").zone("sur").build();
        property4.setOwner(playerImplement);
        property4.setState(new MortgagePropertyState());
        property4.setName("Salta sur");
        property4.setPropertyValue(1500);
        playerImplement.getProperties().add(property3);
        playerImplement.getProperties().add(property4);

        playerStrategy.payOffMortgages(playerImplement);
        assertTrue(property3.getState() instanceof BougthPropertyState);
        assertEquals(4500,playerImplement.getBalance());
        assertTrue(property4.getState() instanceof BougthPropertyState);
    }

    @Test
    public void sellPropertyTest(){
        final String testString = "1" + System.lineSeparator()+"s"+System.lineSeparator()+ "1" + System.lineSeparator()+"n" + System.lineSeparator()+"1" + System.lineSeparator()+"1" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        playerStrategy.sellProperty(playerImplement);

        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Seleccione propiedad a vender:", output[0]);
        assertEquals("Ingrese una de las siguientes opciones", output[1]);
        assertEquals("1: Cordoba norte", output[2]);
        assertEquals("2: Tren Sarmiento", output[3]);
        assertEquals("Seleccionó: Cordoba norte", output[4]);
        assertEquals("Vendiste Cordoba norte", output[5]);
        assertEquals("¿desea vender otra propiedad?", output[6]);

        assertEquals(8500, playerImplement.getBalance());
        assertEquals(5, playerImplement.getProperties().size());
    }

    @Test
    public void decideTest(){
        final String testString = "8" + System.lineSeparator();
        provideInput(testString);
        playerStrategy = new HumanPlayerStrategy();
        Boolean response= playerStrategy.decide(playerImplement);
        assertTrue(response);
    }
}
