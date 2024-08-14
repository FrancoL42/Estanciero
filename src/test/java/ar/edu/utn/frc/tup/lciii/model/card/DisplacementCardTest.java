package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import static org.junit.jupiter.api.Assertions.assertEquals;

import ar.edu.utn.frc.tup.lciii.model.square.DestinySquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import static org.mockito.Mockito.*;

public class DisplacementCardTest {
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
    void executeCard() throws Exception {
        PlayerImplement playerImplement = Mockito.mock(PlayerImplement.class);
        when(playerImplement.getPlayerName()).thenReturn("playerImplement");

        DestinySquare square = new DestinySquare();
        when(playerImplement.getSquare()).thenReturn(square);

        DisplacementCard displacementCard = new DisplacementCard();
        displacementCard.setCardType(1L);
        displacementCard.setCardDescription("DisplacementCard");
        displacementCard.setDisplacement(3);
        displacementCard.executeCard(playerImplement);

        verify(playerImplement).calculateSteps(3);

        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        String outputSuare = "playerImplement se mueve a la casilla " + playerImplement.getSquare().toString();
        assertEquals("DisplacementCard", consoleOutput[0]);
        assertEquals(outputSuare, consoleOutput[1]);





    }
}
