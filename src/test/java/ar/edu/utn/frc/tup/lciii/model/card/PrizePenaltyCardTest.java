package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.*;

import java.io.ByteArrayOutputStream;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.PrintStream;
import java.util.Scanner;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PrizePenaltyCardTest {
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
    void executeCardNegativePenalty() throws Exception {
        PrizePenaltyCard prizePenaltyCard = new PrizePenaltyCard();



        PlayerImplement player = mock(PlayerImplement.class);



        when(player.getPlayerName()).thenReturn("Camila");
        when(player.getBalance()).thenReturn(1000);

        prizePenaltyCard.setCardDescription("prizePenaltyCard");
        prizePenaltyCard.setPrizePenaltyAmount(-50);
        prizePenaltyCard.setCardType(1L);

        prizePenaltyCard.executeCard(player);
        verify(player).updateBalance(-50);

        //String consoleOutput = testOut.toString().trim();
        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("prizePenaltyCard", consoleOutput[0]);
        assertEquals("Camila debe pagar $-50", consoleOutput[1]);
        //assertEquals("prizePenaltyCard" +System.lineSeparator()+"Camila debe pagar $-50", consoleOutput);


    }

    @Test
    void executeCardPositivePenalty() throws Exception {
        PrizePenaltyCard prizePenaltyCard = new PrizePenaltyCard();

        PlayerImplement player = mock(PlayerImplement.class);

        when(player.getPlayerName()).thenReturn("Camila");
        when(player.getBalance()).thenReturn(1000);

        prizePenaltyCard.setCardDescription("prizePenaltyCard");
        prizePenaltyCard.setPrizePenaltyAmount(100);
        prizePenaltyCard.setCardType(1L);

        prizePenaltyCard.executeCard(player);
        verify(player).updateBalance(100);

        //String consoleOutput = testOut.toString().trim();
        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("prizePenaltyCard", consoleOutput[0]);
        assertEquals("Camila obtiene 100", consoleOutput[1]);
        //assertEquals("prizePenaltyCard"+System.lineSeparator()+ "Camila obtiene 100", consoleOutput);


    }

    @Test
    void executeCardNegativeBalance() throws Exception {
        PrizePenaltyCard prizePenaltyCard = new PrizePenaltyCard();


        PlayerImplement player = mock(PlayerImplement.class);


        when(player.getPlayerName()).thenReturn("Camila");
        when(player.getBalance()).thenReturn(100);

        prizePenaltyCard.setCardDescription("prizePenaltyCard");
        prizePenaltyCard.setPrizePenaltyAmount(-150);
        prizePenaltyCard.setCardType(1L);

        prizePenaltyCard.executeCard(player);
        verify(player).updateBalance(-150);


    }
}
