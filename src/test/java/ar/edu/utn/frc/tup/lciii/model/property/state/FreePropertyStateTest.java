package ar.edu.utn.frc.tup.lciii.model.property.state;

import ar.edu.utn.frc.tup.lciii.model.property.RailroadProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FreePropertyStateTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp(){

        System.setOut(new PrintStream(outContent));
    }
    @AfterEach
    public void tearDown(){

        System.setOut(originalOut);
    }

    @Test
    public void testExecuteState(){
//        FreePropertyState state = new FreePropertyState();
//        state.executeState(null,null);
//        assertEquals( "ejecutando.."+System.lineSeparator(),outContent.toString());

    }
    @Test
    public void testNextState(){
//        FreePropertyState state = new FreePropertyState();
//        state.nextState(null);
//        assertEquals("ejecutando.."+System.lineSeparator(),outContent.toString());
    }
}
