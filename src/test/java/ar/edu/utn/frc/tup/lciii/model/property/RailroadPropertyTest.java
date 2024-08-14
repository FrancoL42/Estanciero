package ar.edu.utn.frc.tup.lciii.model.property;

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

public class RailroadPropertyTest {
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
        RailroadProperty  railroal = new RailroadProperty();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setBalance(7000);
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setPlayerName("Franco");
        railroal.setState(new FreePropertyState());
        railroal.executeState(playerImplement);
    }
    @Test
    public void testSetState(){
       new RailroadProperty().setState(new FreePropertyState());

    }
    @Test
    public void testCalculateRent(){
        AbstractProperty property = new RailroadProperty();
        AbstractProperty property2 = new RailroadProperty();
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

        Integer rent = property.calculateRent();
        assertEquals(1400,rent);
    }
}
