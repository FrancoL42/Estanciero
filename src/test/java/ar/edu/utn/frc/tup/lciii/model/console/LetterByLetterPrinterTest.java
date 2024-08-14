package ar.edu.utn.frc.tup.lciii.model.console;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LetterByLetterPrinterTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testPritln() {
        String text = "Hello";
        LetterByLetterPrinter.println(text);
        try{
            Thread.sleep(text.length() * 8 + 50);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        assertEquals(text + System.lineSeparator(), outContent.toString());
    }
    @Test
    public  void  testPrntlnEmptyString(){
        String text = "";
        LetterByLetterPrinter.println(text);
        assertEquals(text + System.lineSeparator(), outContent.toString());
    }
}