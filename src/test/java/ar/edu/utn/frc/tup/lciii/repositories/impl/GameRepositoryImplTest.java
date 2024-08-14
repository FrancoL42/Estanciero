package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.*;
import ar.edu.utn.frc.tup.lciii.entity.converts.CardsConvert;
import ar.edu.utn.frc.tup.lciii.entity.converts.PlayerConvert;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.repositories.CardRepository;
import ar.edu.utn.frc.tup.lciii.repositories.GameRepository;
import ar.edu.utn.frc.tup.lciii.repositories.PropertyRepository;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;
import jakarta.persistence.MapKeyColumn;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GameRepositoryImplTest {

    GameRepository gameRepository = new GameRepositoryImpl();
    CardRepository cardService = new CardRepositoryImpl();
    PropertyRepository propertyRepository = new PropertyRepositoryImpl();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    public void getGameTest(){
        GameEntity game = new GameEntity();
        Long id = Long.parseLong("1");
        game.setId(id);
        GameEntity gameBD = gameRepository.getGame(id);
        assertEquals(game.getId(),gameBD.getId());
    }
    @Disabled
    //TODO
    @Test
    public void getGameTestFail(){
        GameEntity game = new GameEntity();
        Long id = Long.parseLong("2");
        assertNull(gameRepository.getGame(id));

    }

    @Test
    public void getGamesByUserIdTest(){
        List<GameEntity> gameEntities = new ArrayList<>();
        GameEntity gameEntity = new GameEntity();
        gameEntities.add(gameEntity);
        Long id = Long.parseLong("1");
        List<GameEntity> game = gameRepository.getGamesByUserId(id);
        assertNotNull(game);
    }
    @Test
    public void saveTest(){
        List<GameEntity> gameEntities = new ArrayList<>();
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(1L);
        gameEntity.setInitialBalance(1000);
        gameEntity.setVictoryAmount(500000);
        gameEntities.add(gameEntity);
        List<PlayerEntity> players = new ArrayList<>();
        PlayerEntity player1 = new  PlayerEntity();
        player1.setName("player 1");
        player1.setBalance(5000);
        PlayerEntity player2 = new PlayerEntity();
        player2.setName("player 2");
        player2.setBalance(5000);
        players.add(player2);
        players.add(player1);
        List<CardEntity> cards = cardService.getChanceCards();
        List<CardGameEntity> cardGameEntities = cards.stream().map(c-> {
           CardGameEntity cardGame = new CardGameEntity();
           cardGame.setCard(c);
           return cardGame;
        }).toList();
        List<PropertyEntity> propertyEntities = propertyRepository.getAllProperties();
        List<GamePropertyEntity> gamePropertyEntities = propertyEntities.stream().map(p->{
            GamePropertyEntity gameProperty = new GamePropertyEntity();
            gameProperty.setProperty(p);
            return gameProperty;
        }).toList();
       GameEntity game = gameRepository.save(gameEntity, cardGameEntities ,gamePropertyEntities, players );
       assertNotNull(game);
    }

}
