package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.MenuI;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.Strategy;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import org.hibernate.type.YesNoConverter;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.mockito.Mockito.*;

public class GoToSquareCardTest {

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
    void executeCard() throws Exception {
        GoToSquareCard goToSquareCard = new GoToSquareCard();
        goToSquareCard.setNumberSquare(5);
        goToSquareCard.setCardDescription("GoToSquareCard");
        goToSquareCard.setCardType(1L);

        PlayerImplement playerImplement = mock(PlayerImplement.class);

        Board board = Board.getInstance();


        goToSquareCard.executeCard(playerImplement);


        verify(playerImplement).setSquare(board.getSquare(5));

        String consoleOutput = testOut.toString().trim();
        String expectedOutput = "GoToSquareCard";
        assertEquals(expectedOutput, consoleOutput);

    }


}
