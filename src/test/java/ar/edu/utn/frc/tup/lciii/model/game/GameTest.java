package ar.edu.utn.frc.tup.lciii.model.game;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.dice.Dice;
import ar.edu.utn.frc.tup.lciii.model.dice.DiceResult;
import ar.edu.utn.frc.tup.lciii.model.game.builder.AbstractGameBuilder;
import ar.edu.utn.frc.tup.lciii.model.game.builder.EasyGameBuilder;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.square.JailSquare;
import ar.edu.utn.frc.tup.lciii.model.square.LuckSquare;
import ar.edu.utn.frc.tup.lciii.model.square.RestSquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GameTest {
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpOutput(){

        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemOutput(){

        System.setOut(System.out);
    }

    private Game game;
    @BeforeEach
    public void loadPlayersBoard(){
        MockitoAnnotations.openMocks(this);
        game = new Game(1L, Difficulty.EASY, 10000, 1, mock(Queue.class), 5000);

    }

   @Test
   @Disabled
   public void TestGame() {;
    }
    @Disabled
    //TODO
    @Test
    public void startGameTest(){
        game.setTurn(1);
        PlayerImplement playerImplement = createTestPlayer();
        PlayerImplement bot1 = createTestPlayer();
        bot1.setPlayerName("Messi");
        bot1.setSquare(new LuckSquare());
        game.setPlayerTurn(playerImplement);
        Queue<PlayerImplement> playerImplements = new LinkedList<>();
        playerImplements.add(playerImplement);
        playerImplements.add(bot1);

        Board.getInstance().setPlayers(playerImplements);

        Board.getInstance().setNumbersOfSquare(42);
        Board.getInstance().getSquare(42);

         game.startGame();
         assertFalse(game.isFinished());

    }
    //TODO
    @Test
    @Disabled
    public void isFinishedTest(){
        PlayerImplement playerImplement1 = new PlayerImplement();
        playerImplement1.setPlayerName("pepe");
        playerImplement1.setBalance(100000);
        PlayerImplement playerImplement2 = new PlayerImplement();
        playerImplement2.setPlayerName("Pardiaco");
        playerImplement2.setBalance(150000);
        PlayerImplement playerImplement3 = new PlayerImplement();
        playerImplement3.setPlayerName("Ricardito");
        playerImplement3.setBalance(75000);
        Board.getInstance().setPlayer(playerImplement1);
        Board.getInstance().setPlayer(playerImplement2);
        Board.getInstance().setPlayer(playerImplement3);

        game = new Game();
        game.setVictoryAmount(120000);
        game.isFinished();
        assertFalse(Board.getInstance().getPlayers().isEmpty());
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Pardiaco es el ganador con un saldo de 150000!!", output[0]);
        assertTrue(game.getFinishedGame());
   }
    @Disabled
    //TODO
   @Test
   public void playerLoseTrueTest(){
       AbstractGameBuilder easyGameBuilder = new EasyGameBuilder();
       game = easyGameBuilder.getGame();
       Long id = Long.parseLong("1");
       Board.getInstance().setearPlayers(id);
       PlayerImplement pLayer = new PlayerImplement();
       pLayer.setBalance(0);

   }
    @Disabled
    //TODO
    @Test
    public void getPlayersTest(){
        EasyGameBuilder easyGameBuilder = new EasyGameBuilder();
        game = easyGameBuilder.getGame();
   }
    @Disabled
    //TODO
    @Test
    public void getDifficultyTest(){
        EasyGameBuilder easyGameBuilder = new EasyGameBuilder();
        game = easyGameBuilder.getGame();
    }
    @Disabled
    //TODO
    @Test
    public void getVictoryAmountTest(){
        EasyGameBuilder easyGameBuilder = new EasyGameBuilder();
        game = easyGameBuilder.getGame();
        game.getVictoryAmount();
    }
    @Disabled
    //TODO
    @Test
    public void getTurnTest(){
        EasyGameBuilder easyGameBuilder = new EasyGameBuilder();
        game = easyGameBuilder.getGame();
        game.getTurn();
    }
    @Disabled
    //TODO
   @Test
    public void getPropertyTest(){
        EasyGameBuilder easyGameBuilder = new EasyGameBuilder();
        game = easyGameBuilder.getGame();
    }

    @Test
    public void gameTurnTest(){
        try {
            PlayerImplement playerImplement = createTestPlayer();
            game.setPlayerTurn(playerImplement);
            playerImplement.setLoser(false);
            Method method = Game.class.getDeclaredMethod("gameTurn");
            method.setAccessible(true);

            Queue<PlayerImplement> playerImplements = new LinkedList<>();
            playerImplements.add(playerImplement);

            Board.getInstance().setPlayers(playerImplements);

            Board.getInstance().setNumbersOfSquare(42);
            Board.getInstance().getSquare(42);


            Object result = method.invoke(game);

        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.getLocalizedMessage();
        }
    }


    @Test
    public void playerTurnToBoardTest(){
        try {
            PlayerImplement playerImplement = createTestPlayer();
            game.setPlayerTurn(playerImplement);

            Method method = Game.class.getDeclaredMethod("playerTurnToBoard");
            method.setAccessible(true);

            method.invoke(game);

            Queue<PlayerImplement> playersOnBoard = Board.getInstance().getPlayers();
            assertNotNull(playersOnBoard);
            assertTrue(playersOnBoard.contains(playerImplement));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void isRestSquareTest(){
        try {
            PlayerImplement playerImplement = createTestPlayer();
            game.setPlayerTurn(playerImplement);

            Method method = Game.class.getDeclaredMethod("isRestSquare");
            method.setAccessible(true);

            playerImplement.setRestTurnCounter(2);
            Boolean result = (Boolean) method.invoke(game);
            assertFalse(result);
            assertEquals(0, playerImplement.getRestTurnCounter());

            playerImplement.setRestTurnCounter(1);
            playerImplement.setStrategy(new ConservativeBotStrategy());
            result = (Boolean) method.invoke(game);
            assertTrue(result);

            playerImplement.setRestTurnCounter(1);
            playerImplement.setStrategy(new AgressiveBotStrategy());
            result = (Boolean) method.invoke(game);
            assertFalse(result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private PlayerImplement createTestPlayer() {
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Pep");
        playerImplement.setBalance(2000);
        playerImplement.setSquare(new RestSquare());
        playerImplement.setStrategy(new ModerateBotStrategy());
        return playerImplement;
    }

}

