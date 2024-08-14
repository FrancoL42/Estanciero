package ar.edu.utn.frc.tup.lciii.model.console;

import ar.edu.utn.frc.tup.lciii.dtos.GamesDto;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.game.builder.GameBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class MenuGameTest {
    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private ByteArrayOutputStream testOut;
    private User user;

    private MenuI menuI;
    @BeforeEach
    public void setUpOutput() {
        testOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(testOut));
        user = new User();
        user.setId(1L);
        user.setName("pepe");
    }

    @AfterEach
    public void restoreSystemInputOutput() {
        System.setIn(systemIn);
        System.setOut(systemOut);


    }
    private void provideInput(String data) {
        testIn = new ByteArrayInputStream((data + System.lineSeparator()).getBytes());
        System.setIn(testIn);

    }

    private String getOutput() {
        return testOut.toString();
    }


    @Test
    public void selectVictoryModeTest(){
        provideInput("s"+System.lineSeparator()+"1000"+System.lineSeparator()+"100000");

        menuI =  new MenuI();
        menuI.selectVictoryMode();
        String outPut = getOutput();
        assertTrue(outPut.contains("Debe ingresar un monto mayor a 75000 y menor a 150000"));
    }

    @Test
    public void withoutVictoryModeTest(){
        provideInput("n");
        menuI =  new MenuI();
        menuI.selectVictoryMode();
        assertNull(menuI.getVictoryModeAmount());
    }
    @Test
    public void selectNewGame(){

        provideInput("1" +System.lineSeparator() + "s"+ System.lineSeparator()+"100000" +System.lineSeparator() + "1" + System.lineSeparator());
        menuI = new MenuI();
        menuI.setUser(user);
        menuI.setVictoryModeAmount(12000);
        menuI.selectLoadOrNewGame();

        String output = getOutput();
        assertFalse(output.contains("Nueva partida"));

    }
    @Test

    public void selectLoadGame() {
        final String testString = "2" + System.lineSeparator() + "2" + System.lineSeparator() + "10001";
        provideInput(testString);

        menuI = new MenuI();
        menuI.setUser(user);
        menuI.setVictoryModeAmount(15000);
        String output = getOutput();
        assertFalse(output.contains("CargarPartida"));
    }

    @Test
    public void selectDificultHard(){
        final String testString = "3" + System.lineSeparator();
        provideInput(testString);
        menuI = new MenuI();
        menuI.setUser(user);
        menuI.setVictoryModeAmount(15000);
        menuI.selectDifficult(1000);
        String output = getOutput();
        assertTrue(output.contains("Difícil seleccionado"));
    }

    @Test
    public void selectDificultMedium(){
        final String testString = "2" + System.lineSeparator();
        provideInput(testString);
        menuI = new MenuI();
        menuI.setUser(user);
        menuI.setVictoryModeAmount(15000);
        menuI.selectDifficult(1000);
        String output = getOutput();
        assertTrue(output.contains("Medio seleccionado"));
    }
    @Test
    public void selectDificultEasy(){
        final String testString = "1" + System.lineSeparator();
        provideInput(testString);
        menuI = new MenuI();
        menuI.setUser(user);
        menuI.setVictoryModeAmount(15000);
        menuI.selectDifficult(1000);
        String output = getOutput();
        assertTrue(output.contains("Facil seleccionado"));
    }

    @Test
    public void findGamesUserTest(){
        final String testString = "1" + System.lineSeparator();
        provideInput(testString);
        menuI = new MenuI();
        menuI.setUser(user);
        List<GamesDto> games = new ArrayList<>();
        GamesDto game1 = new GamesDto(1L, "game 1");
        GamesDto game2 = new GamesDto(2L,"game 2");
        games.add(game1);
        games.add(game2);
        Long result = menuI.selectGame(games);
        assertEquals(1L,result);
    }

}
