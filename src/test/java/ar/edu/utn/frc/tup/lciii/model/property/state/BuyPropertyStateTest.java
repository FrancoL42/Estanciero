package ar.edu.utn.frc.tup.lciii.model.property.state;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.dice.Dice;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceImpl;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceResult;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.BougthPropertyState;

import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BuyPropertyStateTest {
    private BougthPropertyState buy ;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    @BeforeEach
    public void setUp(){

        System.setOut(new PrintStream(outContent));
        buy = new BougthPropertyState();
    }

    @Test
    public void TestExecuteState(){
    PlayerImplement playerImplement = new PlayerImplement();
    playerImplement.setPlayerID(1L);
    playerImplement.setPlayerName("PEPE");

    AbstractProperty property = new CompanyProperty();
    property.setName("Cordoba");
    property.setRentValue(1000);
    property.setPropertyValue(10000);
    property.setOwner(playerImplement);

    buy.executeState(playerImplement, property);
    assertEquals("La propiedad es de PEPE.No paga nada.",outContent.toString().trim());

    }

    @Test
    public void TestExecuteStateNotOwner(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerID(1L);
        playerImplement.setPlayerName("PEPE");
        playerImplement.setBalance(20000);

        PlayerImplement playerImplement2 = new PlayerImplement();
        playerImplement2.setPlayerID(2L);
        playerImplement2.setPlayerName("Messi");
        playerImplement2.setBalance(20000);

        AbstractProperty property = new CompanyProperty();
        property.setName("Cordoba");
        property.setRentValue(1000);
        property.setPropertyValue(10000);
        property.setOwner(playerImplement);
        playerImplement.getProperties().add(property);

        Board board = Board.getInstance();
        board.setPlayer(playerImplement2);

        DiceResult diceResult = new DiceResult();
        diceResult.setSumDices(6);
        DiceImpl.getInstance().setResult(diceResult);

        buy.executeState(playerImplement2, property);

        assertEquals("Messi debe pagar 600 de alquiler a PEPE",outContent.toString().trim());
        assertEquals(19400, playerImplement2.getBalance());


    }
}
