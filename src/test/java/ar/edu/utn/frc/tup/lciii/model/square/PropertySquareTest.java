package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class PropertySquareTest {

    private PropertySquare propertySquare;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream printStream = new PrintStream(outputStream);
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        propertySquare = new PropertySquare();

    }
    @Test
    public void TestExecuteSquare(){
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Pepe");
        AbstractProperty property = mock(AbstractProperty.class);
        propertySquare.setProperty(property);
        PrintStream originalOut = System.out;
        System.setOut(printStream);
        propertySquare.executeSquare(playerImplement);
        System.setOut(originalOut);
        String capturedOutput = outputStream.toString().trim();
        String expectedOutput = "Pepe cayó en la propiedad "+property.getName();
        assertEquals(expectedOutput, capturedOutput);
    }
}
