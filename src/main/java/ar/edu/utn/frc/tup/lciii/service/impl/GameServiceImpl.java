package ar.edu.utn.frc.tup.lciii.service.impl;

import ar.edu.utn.frc.tup.lciii.dtos.GamesDto;
import ar.edu.utn.frc.tup.lciii.entity.*;
import ar.edu.utn.frc.tup.lciii.entity.converts.CardsConvert;
import ar.edu.utn.frc.tup.lciii.entity.converts.PlayerConvert;
import ar.edu.utn.frc.tup.lciii.entity.converts.PropertyConvert;
import ar.edu.utn.frc.tup.lciii.dtos.ListGameInfoDto;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.repositories.*;
import ar.edu.utn.frc.tup.lciii.repositories.impl.*;
import ar.edu.utn.frc.tup.lciii.service.GameService;

import java.util.ArrayList;
import java.util.List;

public class GameServiceImpl implements GameService {
    GameRepository gameRepository = new GameRepositoryImpl();
    CardRepository cardRepository = new CardRepositoryImpl();
    PropertyRepository propertyRepository = new PropertyRepositoryImpl();
    UserRepository userRepository = new UserRepositoryImpl();

    PlayerRepository playerRepository = new PlayerRepositoryImpl();

    SquareRepository squareRepository = new SquareRepositoryImpl();
    @Override
    public Game getGame(Long igGame) {
        GameEntity gameEntity = gameRepository.getGame(igGame);
        if(gameEntity == null){
            return null;
        }else{
            Game game = new Game();
            game.setGameId(gameEntity.getId());
            DifficultyEntity difficultyEntity = gameEntity.getDifficulty();
            switch (difficultyEntity.getDescription()){
                case "EASY":
                    game.setDifficulty(Difficulty.EASY);
                    break;
                case "MEDIUM":
                    game.setDifficulty(Difficulty.MEDIUM);
                    break;
                case "HARD":
                    game.setDifficulty(Difficulty.HARD);
                    break;
            }
            game.setVictoryAmount(gameEntity.getVictoryAmount());
            return game;
        }

    }

    @Override
    public ListGameInfoDto newGame(Long idUser, Integer balance, Integer victoryAmount, Difficulty difficulty, List<PlayerImplement> players) {
        GameEntity gameEntity = new GameEntity();
        gameEntity.setInitialBalance(balance);
        gameEntity.setVictoryAmount(victoryAmount);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(idUser);
        gameEntity.setUser(userEntity);
        setDifficult(difficulty, gameEntity);
        List<PlayerEntity> playerEntities = players.stream().
                map(PlayerConvert::ConvertToPlayerEntity).toList();

        gameEntity = createGame(playerEntities, gameEntity);

        //convertir todas las listas de gameEntity en models
        players = gameEntity.getPlayers().stream().map(PlayerConvert::ConvertToPlayer).toList();
        List<CardGameEntity> cardsDestiny = gameEntity.getCards().stream().filter(c->c.getCard().getCardType().getId()==2L).toList();
        List<CardGameEntity> cardsChance = gameEntity.getCards().stream().filter(c->c.getCard().getCardType().getId()==1L).toList();

        ListGameInfoDto listGameInfoDto = new ListGameInfoDto();
        listGameInfoDto.setCardsDestiny(cardsDestiny.stream().map(CardsConvert::convertToCard).toList());
        listGameInfoDto.setCardsChance(cardsChance.stream().map(CardsConvert::convertToCard).toList());
        listGameInfoDto.setProperties(gameEntity.getProperties().stream().map(PropertyConvert::ConvertToAbstractProperty).toList());
        listGameInfoDto.setPlayers(players);
        listGameInfoDto.setVictoryAmount(gameEntity.getVictoryAmount());
        listGameInfoDto.setGameId(gameEntity.getId());


        return listGameInfoDto;

    }

    private GameEntity createGame(List<PlayerEntity> players, GameEntity gameEntity) {
        List<CardEntity> cards = cardRepository.getChanceCards();
        cards.addAll(cardRepository.getDestinyCards()) ;
        List<CardGameEntity> cardGameEntity = cards.stream().map(c->{
            CardGameEntity cardGame = new CardGameEntity();
            cardGame.setCard(c);
            return cardGame;
        }).toList();

        List<PropertyEntity> propertyEntities = propertyRepository.getAllProperties();
        List<GamePropertyEntity> gamePropertyEntities = propertyEntities.stream().map(
                p->{
                    GamePropertyEntity gameProperty = new GamePropertyEntity();
                    gameProperty.setProperty(p);
                    return gameProperty;
                }
        ).toList();

        return gameRepository.save(gameEntity,cardGameEntity,gamePropertyEntities, players);
    }

    private  void setDifficult(Difficulty difficulty, GameEntity gameEntity) {
        DifficultyEntity difficultyEntity = new DifficultyEntity();
        switch (difficulty){
            case EASY:
                difficultyEntity.setId(1L);
                difficultyEntity.setDescription("EASY");
                gameEntity.setDifficulty(difficultyEntity);
                break;
            case MEDIUM:
                difficultyEntity.setId(2L);
                difficultyEntity.setDescription("MEDIUM");
                gameEntity.setDifficulty(difficultyEntity);
                break;
            case HARD:
                difficultyEntity.setId(3L);
                difficultyEntity.setDescription("HARD");
                gameEntity.setDifficulty(difficultyEntity);
                break;
        }
    }

    @Override
    public void loadGame(Long idGame) {

    }

    @Override
    public void save(Long gameId,List<AbstractProperty> properties, List<AbstractCard> cards, List<PlayerImplement> playerImplements) {
            GameEntity gameEntity = gameRepository.getGame(gameId);
            saveListProperties(properties, gameEntity);
            saveCardsGame(cards, gameEntity);
            List<PlayerEntity> playerEntities = new ArrayList<>();
            for (PlayerImplement playerImplement :playerImplements){
                PlayerEntity playerEntity = PlayerConvert.ConvertToPlayerEntity(playerImplement);
                playerEntity.setGame(gameEntity);
                Integer number;
                if(playerImplement.getSquare()==null){
                    number=0;
                } else{
                    number = playerImplement.getSquare().getNumberSquare();
                }
                playerEntity.setSquare(squareRepository.getByNumber(number));
                playerEntities.add(playerEntity);
            }
            gameRepository.save(playerEntities);

    }

    @Override
    public List<GamesDto> getAllGames(User user) {
        if(user == null || user.getId() ==null){
            return new ArrayList<>();
        }
        List<GameEntity> gameEntities = gameRepository.getGamesByUserId(user.getId());
        List<GamesDto> gamesDtos = new ArrayList<>();
        if (gameEntities == null){
            return gamesDtos;
        }
        int count = 1;
        for (GameEntity gameEntity: gameEntities){
            GamesDto gamesDto = new GamesDto();
            gamesDto.setId(gameEntity.getId());
            gamesDto.setName("Game " + count);
            gamesDtos.add(gamesDto);
        }
    return gamesDtos;
    }

    private void saveCardsGame(List<AbstractCard> cards,GameEntity game) {
        List<CardGameEntity> cardGameEntities = new ArrayList<>();
        for (AbstractCard card : cards){
            CardGameEntity cardGameEntity = CardsConvert.convertToCardGameEntity(card);
           cardGameEntity.setGame(game);
           if (cardGameEntity.getPlayer() != null){
               PlayerEntity player = playerRepository.getById(cardGameEntity.getPlayer().getId());
               cardGameEntity.setPlayer(player);
           }
           CardEntity cardEntity = cardRepository.getById(card.getCardConfigId());
            cardGameEntity.setCard(cardEntity);
            cardGameEntities.add(cardGameEntity);
        }
        gameRepository.save(cardGameEntities);
    }

    private void saveListProperties(List<AbstractProperty> properties, GameEntity gameEntity) {
        List<GamePropertyEntity> gamePropertyEntityList = new ArrayList<>();

        for (AbstractProperty property: properties){
            GamePropertyEntity propertyEntity = PropertyConvert.ConvertToGamePropertyEntity(property);
            propertyEntity.setProperty(propertyRepository.getById(property.getIdEntityPropertyConfig()));
            if (property.getOwner() !=null){
                propertyEntity.setPlayer(playerRepository.getById(property.getOwner().getPlayerID()));
            }
            propertyEntity.setGame(gameEntity);
            gamePropertyEntityList.add(propertyEntity);
        }
        gameRepository.save(gamePropertyEntityList);
    }


}
