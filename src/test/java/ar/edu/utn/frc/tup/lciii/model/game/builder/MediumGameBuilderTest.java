package ar.edu.utn.frc.tup.lciii.model.game.builder;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MediumGameBuilderTest {
    private MediumGameBuilder mediumGameBuilder;
    private Board board;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp(){
        mediumGameBuilder = new MediumGameBuilder(5);
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

        mediumGameBuilder.setDifficulty(Difficulty.EASY);
       // assertEquals(Difficulty.MEDIUM, MediumGameBuilder.difficulty);
//        assertEquals("dificultad seteada"+ System.lineSeparator(), outContent.toString());
    }
    @Disabled
    //TODO
    @Test
    public void testAddPlayer(){
        mediumGameBuilder.addPlayers(null);
        assertEquals("jugador seteado" + System.lineSeparator(), outContent.toString());
    }
}


