package ar.edu.utn.frc.tup.lciii.model.property;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.game.builder.MediumGameBuilder;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FieldPropertyTest {

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
    public void getNameTest(){
        AbstractProperty fieldProperty = FieldProperty.builder().province("Mendoza").zone("Vino").build();
        assertEquals("Mendoza Vino",fieldProperty.getName());
    }
    @Test
    public void toStringTest(){
        AbstractProperty fieldProperty = FieldProperty.builder().province("Mendoza").zone("Vino").build();
        String s = fieldProperty.toString();
        assertEquals("Mendoza Vino",s);
    }
    @Test
    public void testExecuteState(){
        AbstractProperty fieldProperty = FieldProperty.builder().province("Mendoza").build();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setBalance(7000);
        playerImplement.setStrategy(new ModerateBotStrategy());
        playerImplement.setPlayerName("Franco");
        fieldProperty.setState(new FreePropertyState());
        fieldProperty.executeState(playerImplement);
    }
    @Test
    public void testSetState(){
        new RailroadProperty().setState(new FreePropertyState());

    }
    @Test
    public void testCalculateRentFarm0(){
        AbstractProperty property =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).build();
        AbstractProperty property2 =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).build();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        property.setRentValue(700);
        property.setOwner(playerImplement);
        property2.setRentValue(700);
        property2.setOwner(playerImplement);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        playerImplement.setProperties(propertyList);
        Integer rent = property.calculateRent();
        assertEquals(1400,rent);
    }
    @Test
    public void testCalculateRentFarm1(){
        AbstractProperty property =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(1).build();
        AbstractProperty property2 =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(1).build();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        property.setRentValue(700);
        property.setOwner(playerImplement);
        property2.setRentValue(700);
        property2.setOwner(playerImplement);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        playerImplement.setProperties(propertyList);
        Integer rent = property.calculateRent();
        assertEquals(3500,rent);
    }
    @Test
    public void testCalculateRentFarm2(){
        AbstractProperty property =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(2).build();
        AbstractProperty property2 =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(2).build();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        property.setRentValue(700);
        property.setOwner(playerImplement);
        property2.setRentValue(700);
        property2.setOwner(playerImplement);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        playerImplement.setProperties(propertyList);
        Integer rent = property.calculateRent();
        assertEquals(10500,rent);
    }
    @Test
    public void testCalculateRentFarm3(){
        AbstractProperty property =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(3).build();
        AbstractProperty property2 =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(3).build();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        property.setRentValue(700);
        property.setOwner(playerImplement);
        property2.setRentValue(700);
        property2.setOwner(playerImplement);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        playerImplement.setProperties(propertyList);
        Integer rent = property.calculateRent();
        assertEquals(28000,rent);
    }
    @Test
    public void testCalculateRentFarm4(){
        AbstractProperty property =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(4).build();
        AbstractProperty property2 =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(4).build();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        property.setRentValue(700);
        property.setOwner(playerImplement);
        property2.setRentValue(700);
        property2.setOwner(playerImplement);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        playerImplement.setProperties(propertyList);
        Integer rent = property.calculateRent();
        assertEquals(35000,rent);
    }
    @Test
    public void testCalculateRentFarm5(){
        AbstractProperty property =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(5).build();
        AbstractProperty property2 =  FieldProperty.builder().province("Mendoza").cantPropertiesZones(2).farmCount(5).build();
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Franco");
        property.setRentValue(700);
        property.setOwner(playerImplement);
        property2.setRentValue(700);
        property2.setOwner(playerImplement);
        List<Property> propertyList = new ArrayList<>();
        propertyList.add(property);
        propertyList.add(property2);
        playerImplement.setProperties(propertyList);
        Integer rent = property.calculateRent();
        assertEquals(49000,rent);
    }

}
