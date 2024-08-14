package ar.edu.utn.frc.tup.lciii.model.game;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.dice.Dice;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceResult;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.square.JailSquare;
import ar.edu.utn.frc.tup.lciii.model.square.LuckSquare;
import ar.edu.utn.frc.tup.lciii.model.square.RestSquare;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.ReflectionSupport;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;

public class GameStartTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;
    private ByteArrayInputStream testIn;

    @Mock
    private Dice dice;
    @Mock
    private PlayerImplement playerTurn;
    @Mock
    private HumanPlayerStrategy strategy;

    @InjectMocks
    private PlayerImplement player;

    @InjectMocks
    private Game game;


    @BeforeEach
    public void setUpMockito() {
        player = new PlayerImplement();
        MockitoAnnotations.openMocks(this);// Inicializar los mocks
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


    @Test
    public void isRestSquareTrueTest() throws Exception {
//        RestSquare restSquare = new RestSquare(1L, 1);
//        game.setPlayerTurn(player);
//        player.setRestTurnCounter(1);
//        player.setSquare(restSquare);
//        when(strategy.restYesNo(player)).thenReturn(true);
//        // Utilizando la API de reflexión estándar de Java
//        Method method = Game.class.getDeclaredMethod("isRestSquare");
//        if (method != null) {
//            method.setAccessible(true);
//            Boolean result = (Boolean) method.invoke(game);
//            assertEquals(2, player.getRestTurnCounter());
//            assertTrue(result);
//        } else {
//            fail("Method isRestSquare not found");
//        }
    }


    @Test
    public void isRestSquareFalseTest(){
//        RestSquare restSquare = new RestSquare(1L, 1);
//        game.setPlayerTurn(player);
//        player.setRestTurnCounter(2);
//        player.setSquare(restSquare);
//        when(strategy.restYesNo(player)).thenReturn(true);
//
//        Optional<Method> methodOptional = ReflectionSupport.findMethod(Game.class, "isRestSquare");
//
//        if (methodOptional.isPresent()) {
//            Method method = methodOptional.get();
//            method.setAccessible(true);
//            try{
//                Boolean result = (Boolean) method.invoke(game);
//                assertNull(player.getRestTurnCounter());
//                assertFalse(result);
//            }catch (Exception e){
//
//                fail("Failed to invoke method isRestSquare: " + e.getMessage());
//
//            }
//        } else {
//            fail("Method restSquare not found");
//        }
    }

    @Test
    public void isJailSquareTrueTest(){
//        JailSquare JailSquare = new JailSquare(1L, 1);
//        game.setPlayerTurn(player);
//        player.setTorunGoToJail(1);
//        player.setSquare(JailSquare);
//        when(strategy.restYesNo(player)).thenReturn(true);
//
//        Optional<Method> methodOptional = ReflectionSupport.findMethod(Game.class, "isJailSquare");
//
//        if (methodOptional.isPresent()) {
//            Method method = methodOptional.get();
//            method.setAccessible(true);
//
//            try {
//                Boolean result = (Boolean) method.invoke(game);
//
//                // Verificaciones
//                assertEquals(2, player.getTorunGoToJail());
//                assertTrue(result);
//            } catch (Exception e) {
//                fail("Failed to invoke method isRestSquare: " + e.getMessage());
//            }
//        } else {
//            fail("Method isRestSquare not found");
//        }

    }

    @Test
    public void isFinished(){
     PlayerImplement player1 = new PlayerImplement();
     player1.setBalance(120000);
     player1.setPlayerName("pepe");
        player1.setLoser(false);

        PlayerImplement player2 = new PlayerImplement();
        player2.setBalance(120000);
        player2.setPlayerName("rama");
        player2.setLoser(false);
        PlayerImplement player3 = new PlayerImplement();
        player3.setBalance(89000);
        player3.setPlayerName("agu");
        player3.setLoser(false);

        Board.getInstance().setPlayer(player1);
        Board.getInstance().setPlayer(player2);
        Board.getInstance().setPlayer(player2);

        game = new Game();
        game.setVictoryAmount(0);
        game.setFinishedGame(false);

        assertFalse(game.isFinished());
    }
    @Test
    public void isFinished2(){
        PlayerImplement player1 = new PlayerImplement();
        player1.setBalance(120000);
        player1.setPlayerName("pepe");
        player1.setLoser(false);

        PlayerImplement player2 = new PlayerImplement();
        player2.setBalance(120000);
        player2.setPlayerName("rama");
        player2.setLoser(false);
        PlayerImplement player3 = new PlayerImplement();
        player3.setBalance(89000);
        player3.setPlayerName("agu");
        player3.setLoser(false);

        Board.getInstance().setPlayer(player1);
        Board.getInstance().setPlayer(player2);
        Board.getInstance().setPlayer(player2);

        game = new Game();
        game.setVictoryAmount(100000);
        game.isFinished();
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("pepe es el ganador con un saldo de 120000!!  JUEGO FINALIZADO!!", output[0]);

    }
    @Test
    @Disabled
    public void testGameTurn_threeMirrorRolls() throws Exception {

        DiceResult diceResult = new DiceResult();
        diceResult.setIsMirror(true);
        diceResult.setSumDices(2);
        when(dice.rollDice()).thenReturn(diceResult);
        doNothing().when(playerTurn).calculateSteps(anyInt());
        doNothing().when(playerTurn).executeSquare();
        when(playerTurn.getSquare()).thenReturn(new LuckSquare());
        Board.getInstance().setPlayer(playerTurn);

        game.setPlayerTurn(playerTurn);

        Optional<Method> methodOptional = ReflectionSupport.findMethod(Game.class, "gameTurn");
        if (methodOptional.isPresent()) {
            Method method = methodOptional.get();
            method.setAccessible(true);
            try{
                method.invoke(game);
                verify(playerTurn, times(1)).goToJail();

            } catch (Exception e) {
                fail("Failed to invoke method isRestSquare: " + e.getMessage());

            }
        } else {
            fail("Method jailSquare not found");
        }
        // Assert
    }

}
