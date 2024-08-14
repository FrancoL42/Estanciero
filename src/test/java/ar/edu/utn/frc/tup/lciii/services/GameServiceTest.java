package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.dtos.GamesDto;
import ar.edu.utn.frc.tup.lciii.dtos.ListGameInfoDto;
import ar.edu.utn.frc.tup.lciii.entity.DifficultyEntity;
import ar.edu.utn.frc.tup.lciii.entity.GameEntity;
import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entity.converts.CardsConvert;
import ar.edu.utn.frc.tup.lciii.entity.converts.PlayerConvert;
import ar.edu.utn.frc.tup.lciii.entity.converts.PropertyConvert;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.GoToSquareCard;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.game.builder.EasyGameBuilder;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ModerateBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.square.AbstractSquare;
import ar.edu.utn.frc.tup.lciii.model.square.JailSquare;
import ar.edu.utn.frc.tup.lciii.model.square.RestSquare;
import ar.edu.utn.frc.tup.lciii.repositories.GameRepository;
import ar.edu.utn.frc.tup.lciii.repositories.PlayerRepository;
import ar.edu.utn.frc.tup.lciii.repositories.SquareRepository;
import ar.edu.utn.frc.tup.lciii.service.GameService;
import ar.edu.utn.frc.tup.lciii.service.PlayerService;
import ar.edu.utn.frc.tup.lciii.service.impl.GameServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.PlayerServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.nullable;


import static org.mockito.Mockito.when;


public class GameServiceTest {

    @Mock
    private SquareRepository squareRepositoryMock;

    @Mock
    private PlayerRepository playerRepositoryMock;
    private PlayerService playerService;

    @Mock
    private GameRepository gameRepositoryMock;
    private GameService gameService;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        gameService = new GameServiceImpl();
        playerService = new PlayerServiceImpl();
    }


    @Test
    public void getGame1Test(){
        //todo: metodo funcional, 23/06
        Long id = 1L;
         GameEntity gameEntity = createGameEntity(id,"EASY",500000);
         when(gameRepositoryMock.getGame(id)).thenReturn(gameEntity);
        Game actualGAME = gameService.getGame(id);
        assertNotNull(actualGAME);
        assertEquals(id, actualGAME.getGameId());
        assertEquals(500000, actualGAME.getVictoryAmount());
        assertEquals(Difficulty.EASY, actualGAME.getDifficulty());
    }
    @Disabled
    @Test
    public void getGame2Test(){
        //TODO: YA FUNCIONA ARRIBA
        Long id = Long.parseLong("2");
        Game game = gameService.getGame(id);
        assertNull(game);
    }

    @Test
    @Disabled
    public void newGameTest(){
        Long userId = 2L;
        Integer balance = 1000;
        Integer victoryAmount = 5000;
        Difficulty difficult = Difficulty.EASY;

        PlayerImplement humanPlayer = new PlayerImplement();
        humanPlayer.setPlayerName("Pepe");
        humanPlayer.setStrategy(new HumanPlayerStrategy());
        humanPlayer.setBalance(35000);
        humanPlayer.setInJail(false);
        humanPlayer.setLoser(false);

        PlayerImplement moderatePlayer = new PlayerImplement();
        moderatePlayer.setStrategy(new ModerateBotStrategy());
        moderatePlayer.setBalance(35000);
        moderatePlayer.setPlayerName("Moderado");
        moderatePlayer.setInJail(false);
        moderatePlayer.setLoser(false);

        List<PlayerImplement> playerImplementList = Arrays.asList(
               humanPlayer,moderatePlayer
                );
        GameEntity gameEntityMock = createMockedGameEntity();
        when(gameRepositoryMock.save(any(GameEntity.class),any(),any(),any())).thenReturn(gameEntityMock);

        ListGameInfoDto gameInfoDto = gameService.newGame(userId,balance,victoryAmount,difficult,playerImplementList);


        assertNotNull(gameInfoDto);
    }

    @Test
    public void saveGame(){
        Long gameId =3L;
        GameEntity gameEntity = createGameEntity(gameId,"DIFICIL",10000);
        when(gameRepositoryMock.getGame(gameId)).thenReturn(gameEntity);

        List<AbstractProperty> properties = createTestProperties();
        List<AbstractCard> cards = createTestCards();
        List<PlayerImplement> playerImplements = createTestPlayers();

        gameService.save(gameId,properties,cards,playerImplements);


    }
    private List<AbstractProperty> createTestProperties() {
        List<AbstractProperty> properties = new ArrayList<>();
        AbstractProperty property = new CompanyProperty();
        properties.add(property);
        return properties;
    }

    private List<AbstractCard> createTestCards() {
        List<AbstractCard> cards = new ArrayList<>();
        AbstractCard card = new GoToSquareCard();
        cards.add(card);
        return cards;
    }
    private List<PlayerImplement> createTestPlayers() {
        List<PlayerImplement> playerImplements = new ArrayList<>();
        PlayerImplement playerImplement = new PlayerImplement();
        RestSquare restSquare = new RestSquare();
        playerImplement.setSquare(restSquare);
        playerImplement.setProperties(new ArrayList<>());
        playerImplement.setStrategy(new ConservativeBotStrategy());
        playerImplements.add(playerImplement);
        return playerImplements;
    }

    @Test
    @Disabled
    public void getAllGameTest(){
        User user = new User();
        user.setId(1L);

        List<GameEntity> mockGameEntities = new ArrayList<>();
        //Es incongruente, se podria poner un new gameEntity y listo.. pero bueno queria usarlo
        mockGameEntities.add(createGameEntity(1L,"Dificil",1000));
        when(gameRepositoryMock.getGamesByUserId(user.getId())).thenReturn(mockGameEntities);
        List<GamesDto> result = gameService.getAllGames(user);

        assertEquals(4,result.size());
        assertEquals("Game 1", result.get(0).getName());

    }



    @Test
    public void loadGameTest(){
        List<GamesDto> games = gameService.getAllGames(new User());
        assertTrue(games.isEmpty());

    }


    private GameEntity createGameEntity(Long id, String dificult, int victoryMount){
       GameEntity gameEntity = new GameEntity();
       gameEntity.setId(id);
       gameEntity.setVictoryAmount(victoryMount);

        DifficultyEntity difficultyEntity = new DifficultyEntity();
        difficultyEntity.setDescription(dificult);
        gameEntity.setDifficulty(difficultyEntity);

        return gameEntity;
    }
    private GameEntity createMockedGameEntity() {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setId(1L);
        return gameEntity;
    }
}
