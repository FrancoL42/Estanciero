package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class FreeParkingSquareTest {

    private FreeParkingSquare freeParkingSquare;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private final PrintStream originalOut = System.out;
    @BeforeEach
    public void setUp(){
        freeParkingSquare = new FreeParkingSquare();
        System.setOut(printStream);

    }

    @Test
    public void executeSquareTest(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Pepe");
        freeParkingSquare.executeSquare(playerImplement);
        System.setOut(originalOut);
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Pepe llegó al estacionamiento, no puede quedarse! Se tiran los dados nuevamente!.";
        assertEquals(expectedOutput, capturedOutput);
    }
}
