package ar.edu.utn.frc.tup.lciii.model.game;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.support.ReflectionSupport;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Method;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.fail;

public class GameOrderPlayerTest {

    private Game game;
    @BeforeEach
    public void loadPlayersBoard(){
        game = new Game();
        Board.getInstance().setPlayer(new PlayerImplement());
        Board.getInstance().setPlayer(new PlayerImplement());
        Board.getInstance().setPlayer(new PlayerImplement());
        Board.getInstance().setPlayer(new PlayerImplement());
    }

    @BeforeEach
    public void clear(){
        Board.getInstance().clear();
    }

    @Test
    public void orderPlayerTest(){
        Optional<Method> methodOptional = ReflectionSupport.findMethod(Game.class, "definerPlayerPosition");

        if (methodOptional.isPresent()) {
            Method method = methodOptional.get();
            method.setAccessible(true);
            try{
                method.invoke(game);
                Integer resultDice =13;
                while (true){
                PlayerImplement playerImplement = Board.getInstance().getLastPlayers();
                if(playerImplement==null){
                    break;
                }
                 assertTrue(playerImplement.getDiceResult()<=resultDice);
                 resultDice = playerImplement.getDiceResult();
                }
            }
            catch (Exception e) {
            fail("Failed to invoke method isRestSquare: " + e.getMessage());

            }
        } else {
        fail("Method isRestSquare not found");
        }

    }

}
