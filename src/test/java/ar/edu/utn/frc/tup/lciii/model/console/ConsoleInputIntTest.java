package ar.edu.utn.frc.tup.lciii.model.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
public class ConsoleInputIntTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;

    Console console;
    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);

    }


    @Test
    public void inputIntTest(){
        console = new Console();
       provideInput("42"+System.lineSeparator());
        Integer value1 = console.inputInt();
        assertEquals(42,value1 );

    }

    @Test
    public void inputIntError(){
        console = new Console();
        provideInput( "a"+System.lineSeparator()+"123" + System.lineSeparator());

        Integer value2 = console.inputInt();
        String output = testOut.toString();

        assertTrue(output.contains("Debe ingresar un numero."));
        assertEquals(123, value2);

    }





    @Test
    @Disabled
    void inputOptions(){
        LinkedHashMap<Integer, String > options = new LinkedHashMap<>();
        options.put(1,"option 1");
        options.put(2,"option 2");

        console = new Console();
        provideInput( "a"+ System.lineSeparator()+"3"+ System.lineSeparator()+"1"+System.lineSeparator());
       Integer response = console.selectOptions(options);

        String[] out = testOut.toString().split(System.lineSeparator());
        assertEquals("Ingrese una de las siguientes opciones",out[0]);
        assertEquals("1: option 1", out[1]);
        assertEquals("2: option 2", out[2]);

        assertEquals(1,response);
    }

    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        console = new Console();

    }
}
