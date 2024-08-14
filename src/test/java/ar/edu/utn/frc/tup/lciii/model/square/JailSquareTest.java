package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class JailSquareTest {

    private JailSquare jailSquare;

    @BeforeEach
    public void createSquare(){
        jailSquare = new JailSquare();
    }

    @Test
    public void executeSquareTest(){
        PlayerImplement playerImplement = Mockito.mock(PlayerImplement.class);
        jailSquare.executeSquare( playerImplement);
        verify(playerImplement).goToJail();
    }
}
