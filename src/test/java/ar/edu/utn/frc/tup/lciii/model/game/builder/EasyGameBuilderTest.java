package ar.edu.utn.frc.tup.lciii.model.game.builder;

import ar.edu.utn.frc.tup.lciii.dtos.ListGameInfoDto;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import ar.edu.utn.frc.tup.lciii.service.GameService;
import ar.edu.utn.frc.tup.lciii.service.SquareService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EasyGameBuilderTest {
    //TODO: TESTEAR METODOS DE EASYGAMEBUILDER, ACTUALES CREATOR, ADDPLAYER, LISTPLAYER
    @Mock
    private EasyGameBuilder easyGameBuilder;
    private User user;


    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setName("Test");

    }
    @Test
    public void testConstructorWithVictoryAmount() {
        int victoryAmount = 10;
        easyGameBuilder = new EasyGameBuilder(victoryAmount);
        assertEquals(victoryAmount,easyGameBuilder.getVictoryAmount());
    }

    @Test
    void testContructorWhitUser(){
        int amount = 100;
        int victory = 20;
        ListGameInfoDto expected = new ListGameInfoDto();
        expected.setSquares(null);
        expected.setCardsDestiny(null);
        expected.setCardsChance(null);
        expected.setPlayers(null);
        expected.setProperties(null);
        expected.setGameId(null);
        expected.setVictoryAmount(null);

       EasyGameBuilder builder = new EasyGameBuilder(user,amount,victory);
       ListGameInfoDto actual = builder.getInfoGameDto();
       assertNotNull(builder.getInfoGameDto());
//        assertEquals(expected, actual.getSquares());

    }
    @Test
    @Disabled
    public void testGetPlayers(){
        User user = new User(1L,"user");
        EasyGameBuilder easyGameBuilder = new EasyGameBuilder(user,25000,null);
        BuilderDirector director = new BuilderDirector();
        Game game = easyGameBuilder.getGame();
        director.NewGame(easyGameBuilder);
        Board board = Board.getInstance();
        Board.getInstance().save();
        Board.getInstance().getProperty(6);

    }
    @Disabled
    //TODO
    @Test
    public void testSetDifficulty(){
        User user = new User(2L,"user");
        LoadGameBuilder easyGameBuilder = new LoadGameBuilder(2L);
        BuilderDirector director = new BuilderDirector();
        Game game = easyGameBuilder.getGame();
        director.LoadGame(easyGameBuilder);
        Board board = Board.getInstance();
        Board.getInstance().getProperty(6);    }



}
