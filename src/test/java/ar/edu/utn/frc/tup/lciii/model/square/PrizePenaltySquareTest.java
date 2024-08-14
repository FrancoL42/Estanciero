package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PrizePenaltySquareTest {
    private PrizePenaltySquare prizePenaltySquare;

    @BeforeEach
    public void createSquare(){
        prizePenaltySquare = new PrizePenaltySquare();
    }

    @Test
    public void executeSquareTest() {
        executeSquareTestWithParams(4, -5000, 45000);
        executeSquareTestWithParams(7, -2500, 47500);
        executeSquareTestWithParams(41, -2000, 48000);
    }

    private void executeSquareTestWithParams(int numberSquare, int prizePenaltyAmount, int expectedBalance) {
        prizePenaltySquare.setNumberSquare(numberSquare);
        prizePenaltySquare.setPrizePenaltyAmount(prizePenaltyAmount);
        PlayerImplement playerImplement = new PlayerImplement();
        playerImplement.setPlayerName("Pepe");
        playerImplement.setBalance(50000);
        prizePenaltySquare.executeSquare(playerImplement);
        assertEquals(expectedBalance, playerImplement.getBalance());
    }
}
