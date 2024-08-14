package ar.edu.utn.frc.tup.lciii.model.game.builder;

import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class BuilderDirectorTest {

    private BuilderDirector director;
    private AbstractGameBuilder builder;


    @BeforeEach
    public void setUp(){
        director = new BuilderDirector();
        builder = Mockito.mock(AbstractGameBuilder.class);
    }

    @Test
    public void TestNewGame(){
        //testea y se asegura que se le carguen los recursos
        when(builder.getGame()).thenReturn(new Game());
        when(builder.getGameId()).thenReturn(1L);
        director.NewGame(builder);
        verify(builder).addChanceCards();
        verify(builder).addDestinyCards();
        verify(builder).addPlayers(builder.gameId);
        verify(builder).addSquares();
        verify(builder).addProperties();

    }
    @Test
    @Disabled
    public  void TestLoadGame(){
        //testea y se asegura que se le carguen los recursos, addPlayers devuelve null, pss no hay nada
        when(builder.getGame()).thenReturn(new Game());
        when(builder.getGameId()).thenReturn(1L);
        director.LoadGame(builder);
        verify(builder).addChanceCards();
        verify(builder).addDestinyCards();
        verify(builder).addSquares();
        verify(builder).addProperties();
        verify(builder).addPlayers(1L);
        verifyNoMoreInteractions(builder);
    }


}
