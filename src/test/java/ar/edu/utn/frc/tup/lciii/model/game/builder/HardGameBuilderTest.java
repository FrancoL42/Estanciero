package ar.edu.utn.frc.tup.lciii.model.game.builder;


import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.builder.HardGameBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HardGameBuilderTest {
    private HardGameBuilder hardGameBuilder;
    private Board board;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp(){
        hardGameBuilder = new HardGameBuilder(5);
        board = Board.getInstance();
        board.setPlayers(null);
        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void tearDown(){
        board.setPlayers(null);
        System.setOut(originalOut);
    }
    @Test
    public void testSetDifficulty() {

        hardGameBuilder.setDifficulty(Difficulty.HARD);
        //assertEquals(Difficulty.HARD, hardGameBuilder.difficulty);
//        assertEquals("dificultad seteada"+System.lineSeparator(), outContent.toString());
    }
    @Disabled
    //TODO
    @Test
    public void testAddPlayer(){
        hardGameBuilder.addPlayers(null);
        assertEquals("Jugador seteado"+System.lineSeparator(), outContent.toString());
    }
}
