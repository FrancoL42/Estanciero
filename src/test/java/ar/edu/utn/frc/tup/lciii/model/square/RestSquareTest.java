package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;


import ar.edu.utn.frc.tup.lciii.model.console.Console;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.*;

public class RestSquareTest {
    private RestSquare restSquare;
    private PlayerImplement playerImplement;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    private PrintStream originalOut;
    @BeforeEach
    public void createSquare(){
        restSquare = new RestSquare();
        playerImplement =new PlayerImplement();
        originalOut = System.out;
        System.setOut(printStream);
    }

    @Test
    public void executeSquareTest(){
        playerImplement.setPlayerName("Pepe");
        playerImplement.setRestTurnCounter(0);
        restSquare.executeSquare(playerImplement);
        System.setOut(originalOut);

        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Pepe ha llegado a la casilla de descanso!";
        assertEquals(expectedOutput, capturedOutput);
    }
}
