package ar.edu.utn.frc.tup.lciii.model.property;

import ar.edu.utn.frc.tup.lciii.model.dice.DiceImpl;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceResult;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompanyPropertyTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp(){

        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void tearDown(){

        System.setOut(originalOut);
    }

    @Test
    public void testExecuteState(){
        CompanyProperty  companyProperty = new CompanyProperty();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setBalance(7000);
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setPlayerName("Franco");
        companyProperty.setState(new FreePropertyState());
        companyProperty.executeState(playerImplement);
    }
    @Test
    public void testSetState(){
        new CompanyProperty().setState(new FreePropertyState());

    }
    @Test
    public void testCalculateRent(){
        CompanyProperty property = new CompanyProperty();
        CompanyProperty property2 = new CompanyProperty();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        property.setRentValue(700);
        property.setOwner(playerImplement);
        property2.setRentValue(700);
        property2.setOwner(playerImplement);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        playerImplement.setProperties(propertyList);
        DiceResult diceResult = new DiceResult();
        diceResult.setSumDices(5);
        DiceImpl.getInstance().setResult(diceResult);
        Integer rent = property.calculateRent();
        assertEquals(1000,rent);
    }
}
