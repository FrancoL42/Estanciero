package ar.edu.utn.frc.tup.lciii.model.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class MenuLoginTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;

    private ByteArrayOutputStream testOut;
    private ByteArrayInputStream testIn;

    private MenuI menuI;
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
    public void welcomeTest(){
        final String testString = "1" + System.lineSeparator() + "pepe" + System.lineSeparator() + "1234" + System.lineSeparator();
        provideInput(testString);
        menuI = new MenuI();
        menuI.login();
        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Bienvenido al Estanciero.", output[0]);
        assertEquals("Ingrese una de las siguientes opciones", output[1]);
        assertEquals("1: Ingresar con usuario", output[2]);
        assertEquals("2: Registrarse", output[3]);
        assertEquals("3: Salir",output[4]);
        assertEquals("Seleccionó: Ingresar con usuario", output[5]);
        assertEquals("Ingrese usuario", output[6]);
        assertEquals("Ingrese password", output[7]);
      }

    @Test
    public void registerTest(){
        final String testString = "Ramiro" + System.lineSeparator() + "123" + System.lineSeparator() + "123456" + System.lineSeparator();
        provideInput(testString);
        menuI = new MenuI();
        menuI.register();


        String[] output = testOut.toString().split(System.lineSeparator());
        assertEquals("Ingrese el nombre del usuario a registrar.", output[0]);
        assertEquals("Ingrese contraseña.", output[1]);
        //Captura los datos de hibernate, buscar como sacarlos de consola.
        assertEquals("Se registro el usuario correctamente.", output[output.length-1]);

    }
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream(data.getBytes());
        System.setIn(testIn);
        menuI = new MenuI();
    }


}
