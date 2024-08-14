package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StartingSquareTest {
    @InjectMocks
    private StartingSquare startingSquare;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private PrintStream originalOut;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        startingSquare = new StartingSquare();

        originalOut = System.out;
        System.setOut(printStream);
    }

    @Test
    public void testFirstTimePassingStart() {
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Pepe");
        playerImplement.setBalance(10000);
        startingSquare.executeSquare(playerImplement);
        System.setOut(originalOut);
        String capturedOutPut = outputStream.toString().trim();
        String expectedOutput = "Pepe está en la casilla de salida. Recibe $5000";
        assertEquals(expectedOutput, capturedOutPut);
        assertEquals(15000, playerImplement.getBalance());
    }
}
