package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.Strategy;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.*;

public class OutOfJailCardTest {

    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpOutput(){
        System.setOut(new PrintStream(testOut));
        Board.getInstance().getChanceCards().clear();
    }

    @AfterEach
    public void restoreSystemOutput(){
        System.setOut(System.out);
    }


    @Test
    void executeCardType2() throws Exception {
        PlayerImplement playerImplement = Mockito.mock(PlayerImplement.class);
        when(playerImplement.getPlayerName()).thenReturn("PlayerImplement");
        when(playerImplement.getBalance()).thenReturn(1000);

        OutOfJailCard outOfJailCard = new OutOfJailCard();

        outOfJailCard.setCardDescription("OutOfJailCard");
        outOfJailCard.setCardType(2L);

        Board board = Board.getInstance();
        doNothing().when(playerImplement).setCardToList(any());
        outOfJailCard.executeCard(playerImplement);


        String consoleOutput = testOut.toString().trim();
        assertEquals("OutOfJailCard", consoleOutput);

        assertEquals(0, board.getDestinyCards().size());

        verify(playerImplement).setCardToList(outOfJailCard);

    }

    @Disabled
    @Test
    void executeCardType1() throws Exception {
        PlayerImplement playerImplement = Mockito.mock(PlayerImplement.class);
        when(playerImplement.getPlayerName()).thenReturn("PlayerImplement");
        when(playerImplement.getBalance()).thenReturn(1000);

        OutOfJailCard outOfJailCard = new OutOfJailCard();

        outOfJailCard.setCardDescription("OutOfJailCard");
        outOfJailCard.setCardType(1L);

        Board board = Board.getInstance();
        doNothing().when(playerImplement).setCardToList(any());
        outOfJailCard.executeCard(playerImplement);


        String consoleOutput = testOut.toString().trim();
        assertEquals("OutOfJailCard", consoleOutput);

        assertEquals(0, board.getChanceCards().size());

        verify(playerImplement).setCardToList(outOfJailCard);

    }
}
