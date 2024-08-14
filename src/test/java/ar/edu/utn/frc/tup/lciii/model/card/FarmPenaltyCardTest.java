package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import static org.junit.jupiter.api.Assertions.assertEquals;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.mockito.Mockito.*;

public class FarmPenaltyCardTest {
    private final ByteArrayOutputStream testOut = new ByteArrayOutputStream();

    @BeforeEach
    public void setUpOutput(){
        System.setOut(new PrintStream(testOut));
    }

    @AfterEach
    public void restoreSystemOutput(){
        System.setOut(System.out);
    }

    @Test
    void executeCard1() throws Exception {
        FieldProperty fieldProperty = mock(FieldProperty.class);
        when(fieldProperty.getFarmCount()).thenReturn(2);
        when(fieldProperty.getName()).thenReturn("fieldProperty");

        FieldProperty fieldProperty1 = mock(FieldProperty.class);
        when(fieldProperty1.getFarmCount()).thenReturn(3);
        when(fieldProperty1.getName()).thenReturn("fieldProperty1");

        CompanyProperty companyProperty = mock(CompanyProperty.class);

        List<Property> properties = new ArrayList<>();
        properties.add(fieldProperty);
        properties.add(fieldProperty1);
        properties.add(companyProperty);


        PlayerImplement player = Mockito.mock(PlayerImplement.class);
        when(player.getBalance()).thenReturn(1000);
        when(player.getPlayerName()).thenReturn("PlayerImplement");
        when(player.getProperties()).thenReturn(properties);



        FarmPenaltyCard farmPenaltyCard = new FarmPenaltyCard();
        farmPenaltyCard.setCardDescription("FarmPenaltyCard");
        farmPenaltyCard.setValuePenalty(-100);
        farmPenaltyCard.setCardType(1L);

        farmPenaltyCard.executeCard(player);
        verify(player).updateBalance(anyInt());

        /*String expectedOutput = "FarmPenaltyCard\r\nPlayerImplement tiene 2 chacras en fieldProperty\r\n" +
                "PlayerImplement tiene 3 chacras en fieldProperty1";
        String consoleOutput = testOut.toString().trim();
        assertEquals(expectedOutput, consoleOutput);*/

        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("FarmPenaltyCard", consoleOutput[0]);
        assertEquals("PlayerImplement tiene 2 chacras en fieldProperty", consoleOutput[1]);
        assertEquals("PlayerImplement tiene 3 chacras en fieldProperty1", consoleOutput[2]);
        assertEquals("PlayerImplement debe pagar -500", consoleOutput[3]);

    }


    @Test
    void executeCard2() throws Exception {
        CompanyProperty property1 = mock(CompanyProperty.class);
        //when(fieldProperty.getFarmCount()).thenReturn(2);
        //when(fieldProperty.getName()).thenReturn("fieldProperty");

        CompanyProperty property2 = mock(CompanyProperty.class);
        //when(fieldProperty1.getFarmCount()).thenReturn(3);
        //when(fieldProperty1.getName()).thenReturn("fieldProperty1");

        CompanyProperty companyProperty = mock(CompanyProperty.class);

        List<Property> properties = new ArrayList<>();
        properties.add(property1);
        properties.add(property1);
        properties.add(companyProperty);


        PlayerImplement player = Mockito.mock(PlayerImplement.class);
        when(player.getBalance()).thenReturn(1000);
        when(player.getPlayerName()).thenReturn("PlayerImplement");
        when(player.getProperties()).thenReturn(properties);



        FarmPenaltyCard farmPenaltyCard = new FarmPenaltyCard();
        farmPenaltyCard.setCardDescription("FarmPenaltyCard");
        farmPenaltyCard.setValuePenalty(-100);
        farmPenaltyCard.setCardType(1L);

        farmPenaltyCard.executeCard(player);
        verify(player).updateBalance(anyInt());

        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("FarmPenaltyCard", consoleOutput[0]);
        assertEquals("PlayerImplement debe pagar 0", consoleOutput[1]);



    }
    @Test
    void executeCard3() throws Exception {
        FieldProperty fieldProperty = mock(FieldProperty.class);
        when(fieldProperty.getFarmCount()).thenReturn(2);
        when(fieldProperty.getName()).thenReturn("fieldProperty");

        FieldProperty fieldProperty1 = mock(FieldProperty.class);
        when(fieldProperty1.getFarmCount()).thenReturn(8);
        when(fieldProperty1.getName()).thenReturn("fieldProperty1");

        CompanyProperty companyProperty = mock(CompanyProperty.class);

        List<Property> properties = new ArrayList<>();
        properties.add(fieldProperty);
        properties.add(fieldProperty1);
        properties.add(companyProperty);


        PlayerImplement player = Mockito.mock(PlayerImplement.class);
        when(player.getBalance()).thenReturn(1000);
        when(player.getPlayerName()).thenReturn("PlayerImplement");
        when(player.getProperties()).thenReturn(properties);



        FarmPenaltyCard farmPenaltyCard = new FarmPenaltyCard();
        farmPenaltyCard.setCardDescription("FarmPenaltyCard");
        farmPenaltyCard.setValuePenalty(-100);
        farmPenaltyCard.setCardType(1L);

        farmPenaltyCard.executeCard(player);
        verify(player).updateBalance(anyInt());

        /*String expectedOutput = "FarmPenaltyCard\r\nPlayerImplement tiene 2 chacras en fieldProperty\r\n" +
                "PlayerImplement tiene estancias en fieldProperty1";
        String consoleOutput = testOut.toString().trim();
        assertEquals(expectedOutput, consoleOutput);*/

        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("FarmPenaltyCard", consoleOutput[0]);
        assertEquals("PlayerImplement tiene 2 chacras en fieldProperty", consoleOutput[1]);
        assertEquals("PlayerImplement tiene estancia en fieldProperty1", consoleOutput[2]);
        assertEquals("PlayerImplement debe pagar -1000", consoleOutput[3]);

    }

    @Test
    void executeCard4() throws Exception {
        FieldProperty fieldProperty = mock(FieldProperty.class);
        when(fieldProperty.getFarmCount()).thenReturn(2);
        when(fieldProperty.getName()).thenReturn("fieldProperty");

        FieldProperty fieldProperty1 = mock(FieldProperty.class);
        when(fieldProperty1.getFarmCount()).thenReturn(8);
        when(fieldProperty1.getName()).thenReturn("fieldProperty1");

        CompanyProperty companyProperty = mock(CompanyProperty.class);

        List<Property> properties = new ArrayList<>();
        properties.add(fieldProperty);
        properties.add(fieldProperty1);
        properties.add(companyProperty);


        PlayerImplement player = Mockito.mock(PlayerImplement.class);
        when(player.getBalance()).thenReturn(0);
        when(player.getPlayerName()).thenReturn("PlayerImplement");
        when(player.getProperties()).thenReturn(properties);



        FarmPenaltyCard farmPenaltyCard = new FarmPenaltyCard();
        farmPenaltyCard.setCardDescription("FarmPenaltyCard");
        farmPenaltyCard.setValuePenalty(-100);
        farmPenaltyCard.setCardType(1L);

        farmPenaltyCard.executeCard(player);
        verify(player).updateBalance(anyInt());

        /*String expectedOutput = "FarmPenaltyCard\r\nPlayerImplement tiene 2 chacras en fieldProperty\r\n" +
                "PlayerImplement tiene estancias en fieldProperty1";
        String consoleOutput = testOut.toString().trim();
        assertEquals(expectedOutput, consoleOutput);*/

        String[] consoleOutput = testOut.toString().split(System.lineSeparator());
        assertEquals("FarmPenaltyCard", consoleOutput[0]);
        assertEquals("PlayerImplement tiene 2 chacras en fieldProperty", consoleOutput[1]);
        assertEquals("PlayerImplement tiene estancia en fieldProperty1", consoleOutput[2]);
        assertEquals("PlayerImplement debe pagar -1000", consoleOutput[3]);

    }

}
