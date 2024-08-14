package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class GoToJailSquareTest {
    GoToJailSquare goToJailSquare;

    @BeforeEach
    public void createSquare(){
        goToJailSquare = new GoToJailSquare();
    }

    @Test
    public void executeSquareTest(){
        PlayerImplement playerImplement = Mockito.mock(PlayerImplement.class);
        goToJailSquare.executeSquare(playerImplement);
        verify(playerImplement, times(1)).goToJail();

    }
}
