package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.Queue;

public class PrizePerPlayerCardTest {
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpOutput(){

        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemOutput(){

        System.setOut(System.out);
    }


    @Test
    public void TestExecuteCardPlayer1(){
        PlayerImplement player1 = Mockito.mock(PlayerImplement.class);
        PlayerImplement player2 = Mockito.mock(PlayerImplement.class);
        PlayerImplement player3 = Mockito.mock(PlayerImplement.class);

        when(player1.getPlayerName()).thenReturn("Player1");
        when(player2.getPlayerName()).thenReturn("Player2");
        when(player3.getPlayerName()).thenReturn("Player3");

        when(player1.isLoser()).thenReturn(false);
        when(player2.isLoser()).thenReturn(false);
        when(player3.isLoser()).thenReturn(true);

        when(player1.getBalance()).thenReturn(0);
        when(player2.getBalance()).thenReturn(100);
        when(player3.getBalance()).thenReturn(0);

        Board board = Board.getInstance();
        Queue<PlayerImplement> players = new ArrayDeque<>();
        //players.add(player1);
        players.add(player2);
        players.add(player3);
        board.setPlayers(players);


        PrizePerPlayerCard prizePerPlayerCard = new PrizePerPlayerCard();
        prizePerPlayerCard.setCardDescription("PrizePerPlayerCard");
        prizePerPlayerCard.setPrizePerPlayerAmount(100);
        prizePerPlayerCard.setCardType(1L);

        prizePerPlayerCard.executeCard(player1);
        verify(player1).updateBalance(anyInt());
        verify(player2).updateBalance(anyInt());


        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("PrizePerPlayerCard", consoleOutput[0]);
        assertEquals("Player1 obtiene 100 del resto de los jugadores.", consoleOutput[1]);


        /*String consoleOutput = testOut.toString().trim();
        String expectedOutput = "PrizePerPlayerCard\r\nPlayer1 obtiene 100 del resto de los jugadores.";
        assertEquals(expectedOutput, consoleOutput);*/


    }


    @Test
    public void TestExecuteCardPlayer2(){
        PlayerImplement player1 = Mockito.mock(PlayerImplement.class);
        PlayerImplement player2 = Mockito.mock(PlayerImplement.class);
        PlayerImplement player3 = Mockito.mock(PlayerImplement.class);

        when(player1.getPlayerName()).thenReturn("Player1");
        when(player2.getPlayerName()).thenReturn("Player2");
        when(player3.getPlayerName()).thenReturn("Player3");

        when(player1.isLoser()).thenReturn(false);
        when(player2.isLoser()).thenReturn(false);
        when(player3.isLoser()).thenReturn(false);

        when(player1.getBalance()).thenReturn(2000);
        when(player2.getBalance()).thenReturn(2000);
        when(player3.getBalance()).thenReturn(2000);

        Board board = Board.getInstance();
        Queue<PlayerImplement> players = new ArrayDeque<>();
        players.add(player1);
        //players.add(player2);
        players.add(player3);
        board.setPlayers(players);


        PrizePerPlayerCard prizePerPlayerCard = new PrizePerPlayerCard();
        prizePerPlayerCard.setCardDescription("PrizePerPlayerCard");
        prizePerPlayerCard.setPrizePerPlayerAmount(2000);
        prizePerPlayerCard.setCardType(1L);

        prizePerPlayerCard.executeCard(player2);
        verify(player1).updateBalance(anyInt());
        verify(player2).updateBalance(anyInt());
        verify(player3).updateBalance(anyInt());

        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("PrizePerPlayerCard", consoleOutput[0]);
        assertEquals("Player2 obtiene 4000 del resto de los jugadores.", consoleOutput[1]);

        /*String consoleOutput = testOut.toString().trim();
        String expectedOutput = "PrizePerPlayerCard\r\nPlayer2 obtiene 4000 del resto de los jugadores.";
        assertEquals(expectedOutput, consoleOutput);*/


    }
}

