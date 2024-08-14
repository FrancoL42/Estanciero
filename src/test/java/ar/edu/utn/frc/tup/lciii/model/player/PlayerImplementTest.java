package ar.edu.utn.frc.tup.lciii.model.player;


import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.card.DisplacementCard;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.Strategy;
import ar.edu.utn.frc.tup.lciii.model.property.*;
import ar.edu.utn.frc.tup.lciii.model.square.*;
import org.hibernate.boot.CacheRegionDefinition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerImplementTest {

    private PlayerImplement implement;
    private ByteArrayOutputStream testOut;
    @Mock
    private AbstractProperty property;
    @Mock
        private List<AbstractProperty> properties;
    @Mock
    private Card card;
    @Mock
    private Square square;

    @Mock
    private Strategy strategy;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        strategy = mock(Strategy.class);
        implement = new PlayerImplement();
        implement.setStrategy(strategy);

    }
    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));

    }
    @Test
    public void testSettingPlayer(){
        PlayerImplement playerImplement= new PlayerImplement();
        playerImplement.setPlayerID(1L);
        playerImplement.setBalance(200);
        playerImplement.setStrategy(new HumanPlayerStrategy());
        playerImplement.setCards(new ArrayList<>());
        playerImplement.setProperties(new ArrayList<>());
        playerImplement.setSquare(new StartingSquare());
        playerImplement.setPlayerName("Pablo");
        playerImplement.getPlayerID();
        playerImplement.getPlayerName();
        playerImplement.getSquare();
        playerImplement.getBalance();
        playerImplement.getCards();
        playerImplement.getStrategy();
        playerImplement.getSquare();

    }
    @Test
    @Disabled
    public void testRollDice(){
    }
    @Test
    public void testUseCard(){
        implement.useCard();
    }
    @Test
    public void testBuyProperty(){
        //implement.buyProperty(property);
        //verify(strategy).buyProperty(property,implement);
    }
    @Test
    public void testMortgageProperty(){
        implement.mortgageProperty();
        List<AbstractProperty> properties = new ArrayList<>();
        //verify(strategy).mortGageProperty(properties, new PlayerImplement());
    }
    @Test
    public void testSellFarm(){
        implement.sellFarm();
        verify(strategy).sellFarm(implement);
    }
    @Test
    public void testCalculateStepsInicio(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(-1);
        AbstractSquare abstractSquare = new StartingSquare();
        abstractSquare.setNumberSquare(0);
        playerImplement.setSquare(abstractSquare);
        Board.getInstance().setNumbersOfSquare(0);
        playerImplement.calculateSteps(2);
        assertEquals(4999,playerImplement.getBalance());

    }

    @Test
    public void testCalculateStepsInicioSetSquare(){
        PlayerImplement playerImplement = new PlayerImplement();
        Map<Integer,Square> squares = new HashMap<>();
        AbstractSquare square1 = new LuckSquare();
        square1.setNumberSquare(1);
        squares.put(1,square1);
        AbstractSquare square2 = new DestinySquare();
        square2.setNumberSquare(0);
        squares.put(0,square2);
        AbstractSquare square3 = new GoToJailSquare();
        square3.setNumberSquare(2);
        squares.put(2,square3);
        AbstractSquare square4 = new RestSquare();
        square4.setNumberSquare(3);
        squares.put(3,square4);
        AbstractSquare square5 = new DestinySquare();
        square5.setNumberSquare(4);
        squares.put(4,square5);
        AbstractSquare square6 = new LuckSquare();
        square6.setNumberSquare(5);
        squares.put(5,square6);
        AbstractSquare square7 = new GoToJailSquare();
        square7.setNumberSquare(6);
        squares.put(6,square7);
        AbstractSquare square8 = new StartingSquare();
        square8.setNumberSquare(7);
        squares.put(7,square8);
        AbstractSquare square9 = new FreeParkingSquare();
        square9.setNumberSquare(8);
        squares.put(8,square9);

        Board.getInstance().setSquares(squares);
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(-1);

        playerImplement.setSquare(square2);
        Board.getInstance().setNumbersOfSquare(8);
        playerImplement.calculateSteps(7);
        playerImplement.calculateSteps(1);
        playerImplement.calculateSteps(1);
        playerImplement.calculateSteps(3);
        playerImplement.calculateSteps(3);




    }
    @Test
    public void showFieldStatsTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        List<Property> propertyList = new ArrayList<>();
        AbstractProperty abstractProperty = FieldProperty.builder().build();
        abstractProperty.setName("Fr");
        propertyList.add(abstractProperty);
        playerImplement.setProperties(propertyList);
        playerImplement.showFieldsStats();
        String output = testOut.toString();
        assertTrue(output.contains("chacras"));
    }
    @Test
    public void showFieldStatsTest2(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        List<Property> propertyList = new ArrayList<>();
        AbstractProperty abstractProperty = FieldProperty.builder().farmCount(5).build();
        abstractProperty.setName("Fr");

        propertyList.add(abstractProperty);
        playerImplement.setProperties(propertyList);
        playerImplement.showFieldsStats();
        String output = testOut.toString();
        assertTrue(output.contains("estancia"));
    }
    @Test
    public void setCardToListTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setCardToList(new DisplacementCard());
        assertFalse(playerImplement.getCards().isEmpty());
    }
    @Test
    public void showStatCards(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setCardToList(new DisplacementCard());
        playerImplement.showCardsStats();
        String output = testOut.toString();
        assertTrue(output.contains("carcel"));
    }
    @Test
    public void testCalulateSteps2(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(-1);
        AbstractSquare abstractSquare = new StartingSquare();
        abstractSquare.setNumberSquare(-3);
        playerImplement.setSquare(abstractSquare);
        Board.getInstance().setNumbersOfSquare(0);
        playerImplement.calculateSteps(2);
        assertNotEquals(abstractSquare,playerImplement.getSquare());
    }
    @Test
    public void showRailroadStats(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(200);
        List<Property> propertyList = new ArrayList<>();
        AbstractProperty abstractProperty = new RailroadProperty();
        abstractProperty.setName("Fr");
        propertyList.add(abstractProperty);
        playerImplement.setProperties(propertyList);
        playerImplement.showRailRoadStats();
        String output = testOut.toString();
        assertTrue(output.contains("ferrocarriles"));
    }
    @Test
    public void updateBalanceTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(-1);
        playerImplement.updateBalance(0);
        assertTrue(playerImplement.isLoser());
    }
    @Test
    public void areAllPropertiesMortgagedTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(200);
        List<Property> propertyList = new ArrayList<>();
        AbstractProperty abstractProperty = new CompanyProperty();
        propertyList.add(abstractProperty);
        playerImplement.setProperties(propertyList);
        assertTrue(playerImplement.areAllPropertiesMortgaged());
    }
    @Test
    public void sellCardTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(-1);
        playerImplement.setSquare(new StartingSquare());
        List<Card> cards = new ArrayList<>();
        AbstractCard card1 = new DisplacementCard();
        card1.setCardType(Long.parseLong("1"));
        cards.add(card1);
        playerImplement.setCards(cards);
        List<Property> propertyList = new ArrayList<>();
        AbstractProperty abstractProperty = new CompanyProperty();
        abstractProperty.setName("Fr");
        propertyList.add(abstractProperty);
        playerImplement.setProperties(propertyList);
        playerImplement.sellCard();
        assertEquals(999,playerImplement.getBalance());
    }
    @Test
    public void showStatsTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(500);
        playerImplement.setSquare(new StartingSquare());
        List<Property> propertyList = new ArrayList<>();
        AbstractProperty abstractProperty = new CompanyProperty();
        abstractProperty.setName("Fr");
        propertyList.add(abstractProperty);
        playerImplement.setProperties(propertyList);
        playerImplement.showStats();
        String output = testOut.toString();
        assertTrue(output.contains(" tiene las siguientes propiedades:"));
    }
    @Test
    public void executeSquareTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(500);
        playerImplement.setSquare(new StartingSquare());
        playerImplement.executeSquare();
        assertEquals(5500,playerImplement.getBalance());
    }
    @Test
    public void showCompanyStatsTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(500);
        List<Property> propertyList = new ArrayList<>();
        AbstractProperty abstractProperty = new CompanyProperty();
        abstractProperty.setName("Fr");
        propertyList.add(abstractProperty);
        playerImplement.setProperties(propertyList);
        playerImplement.showCompanyStats();
        String output = testOut.toString();
        assertTrue(output.contains("Tiene 1 compañias."));
    }
    @Test
    public void goToJailTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setBalance(500);
        playerImplement.goToJail();
        assertTrue(playerImplement.isInJail());
    }
    @Test
    public void decideTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setStrategy(new ModerateBotStrategy());
        Map<Integer,AbstractProperty> propertyList = new HashMap<>();
        propertyList.put(1,new RailroadProperty());
        Board.getInstance().setProperties(propertyList);
        assertFalse(playerImplement.decide());
    }

}
