package ar.edu.utn.frc.tup.lciii.model.game.builder;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Game;

import org.junit.jupiter.api.Test;

public class LoadGameBuilderTest {



    @Test
    public void TestAddChanceCards() {
            Board.getInstance().clear();
            User user = new User(1L,"user");
            HardGameBuilder easyGameBuilder = new HardGameBuilder(user,25000,null);
            BuilderDirector director = new BuilderDirector();
            Game game = easyGameBuilder.getGame();
            director.NewGame(easyGameBuilder);
            Board.getInstance().save();
            Board.getInstance().clear();

            Board.getInstance().clear();
            LoadGameBuilder load = new LoadGameBuilder(game.getGameId());
            director.LoadGame(load);
//            assertFalse(Board.getInstance().getDestinyCards().isEmpty());
//            assertFalse(Board.getInstance().getChanceCards().isEmpty());
//            assertFalse(Board.getInstance().getSquares().isEmpty());
//            assertFalse(Board.getInstance().getProperties().isEmpty());


    }

}
