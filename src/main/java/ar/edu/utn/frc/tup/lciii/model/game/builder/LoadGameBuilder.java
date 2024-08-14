package ar.edu.utn.frc.tup.lciii.model.game.builder;


import ar.edu.utn.frc.tup.lciii.dtos.ListGameInfoDto;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.square.JailSquare;
import ar.edu.utn.frc.tup.lciii.model.square.PropertySquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import ar.edu.utn.frc.tup.lciii.service.GameService;
import ar.edu.utn.frc.tup.lciii.service.PlayerService;
import ar.edu.utn.frc.tup.lciii.service.PropertyService;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.PlayerServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.PropertyServiceImpl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

public class LoadGameBuilder extends AbstractGameBuilder {

    //hay que sacar el parametro gameId por que hay que pedirlo despues para buscar los juegos

    PlayerService playerService = new PlayerServiceImpl();
    PropertyService propertyService = new PropertyServiceImpl();

    CardService cardService = new CardServiceImpl();
    public LoadGameBuilder(Long gameId) {
        this.gameId = gameId;
        infoGameDto = new ListGameInfoDto();
        infoGameDto.setGameId(gameId);
        Game game = gameService.getGame(gameId);
        infoGameDto.setVictoryAmount(game.getVictoryAmount());
    }
    /**
     * Setea el servicio
     */

    @Override
    public void addChanceCards() {
        List<PlayerImplement> playerImplements = infoGameDto.getPlayers();

        List<AbstractCard> cards = cardService.getAllChanceCardsByGame(gameId, playerImplements);
        cards.sort(Comparator.comparing(AbstractCard::getOrder));
        this.chanceCards.addAll(cards);
        Board.getInstance().setChanceCards(this.chanceCards);    }

    @Override
    public void addDestinyCards() {
        List<PlayerImplement> playerImplements = infoGameDto.getPlayers();

        List<AbstractCard> cards = cardService.getAllDestinyCardsByGame(gameId, playerImplements);
        cards.sort(Comparator.comparing(AbstractCard::getOrder));
        this.destinyCards.addAll(cards);
        Board.getInstance().setDestinyCards(this.destinyCards);
    }

    @Override
    public void addPlayers(Long gameid) {

       List<PlayerImplement> players = playerService.getAllPlayer(gameId);
       infoGameDto.setPlayers(players);
       players.sort(Comparator.comparing(PlayerImplement::getOrderTurn));
        infoGameDto.getPlayers().forEach(p-> {
            Board.getInstance().setPlayer(p);
        });
    }



    @Override
    public void addSquares() {
       List<Square> squaresList = squareService.getAllSquare(properties.values().stream().toList());
        squaresList.forEach(s-> {
            if(s instanceof JailSquare){
                JailSquare jail = (JailSquare) s;
                Board.getInstance().setJail(jail);
            }
            squares.put(s.getNumberSquare(), s);
        });
        Board.getInstance().setSquares(this.squares);
        Board.getInstance().setNumbersOfSquare(this.squares.size()-1);
        infoGameDto.getPlayers().stream().forEach(p->{
            p.setSquare(Board.getInstance().getSquare(p.getNumberSquare()));
        });
    }



    @Override
    public void addProperties() {

        List<PlayerImplement> playerImplements = infoGameDto.getPlayers();
        List<AbstractProperty> propertiesList = propertyService.getAllProperty(gameId, playerImplements);
        infoGameDto.setProperties(propertiesList);
        propertiesList.forEach(p->properties.put(p.getPropertyId().intValue(),p));
        Board.getInstance().setProperties(properties);
    }


}
