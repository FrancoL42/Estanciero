package ar.edu.utn.frc.tup.lciii.model.game.builder;

import ar.edu.utn.frc.tup.lciii.dtos.ListGameInfoDto;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Difficulty;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.square.JailSquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import ar.edu.utn.frc.tup.lciii.service.GameService;
import ar.edu.utn.frc.tup.lciii.service.SquareService;
import ar.edu.utn.frc.tup.lciii.service.impl.GameServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.SquareServiceImpl;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import java.util.*;
import java.util.Queue;
import java.util.concurrent.LinkedTransferQueue;

/**
 * Clase molde para los game builder.Tiene como atributos todos aquellos necesarios para generar un nuevo game
 * que sera instanciado por el metodo getGame();
 */
@Getter
@Setter
public abstract class AbstractGameBuilder implements GameBuilder {
    protected Long gameId;
    protected Difficulty difficulty;
    protected int victoryAmount;
    protected int turn;
    protected Queue<PlayerImplement> players = new LinkedTransferQueue<>();
    protected Queue<Card> destinyCards = new ArrayDeque<>();
    protected Queue<Card> chanceCards = new ArrayDeque<>();
    protected HashMap<Integer, AbstractProperty> properties = new HashMap<>();
    protected HashMap<Integer, Square> squares = new HashMap<>();
    protected GameService gameService = new GameServiceImpl();
    protected Integer initialBalance;
    protected ListGameInfoDto infoGameDto;
    protected SquareService squareService = new SquareServiceImpl();

    @Override
    public void addChanceCards() {
        List<AbstractCard> cardsChance = new ArrayList<>(infoGameDto.getCardsChance());
        Collections.shuffle(cardsChance);
        for (int i = 0; i < cardsChance.size(); i++) {
            cardsChance.get(i).setOrder(i);
        }
        cardsChance.sort(Comparator.comparing(AbstractCard::getOrder));
        this.chanceCards.addAll(cardsChance);
        Board.getInstance().setChanceCards(this.chanceCards);
    }

    @Override
    public void addDestinyCards() {
        List<AbstractCard> cardsDestiny = new ArrayList<>(infoGameDto.getCardsDestiny());
        Collections.shuffle(cardsDestiny);
        for (int i = 0; i < cardsDestiny.size(); i++) {
            cardsDestiny.get(i).setOrder(i);
        }
        cardsDestiny.sort(Comparator.comparing(AbstractCard::getOrder, Comparator.nullsLast(Comparator.naturalOrder())));
        this.destinyCards.addAll(cardsDestiny);
        Board.getInstance().setDestinyCards(this.destinyCards);
    }


    @Override
    public void addSquares() {
        infoGameDto.getSquares().forEach(s -> {
            if (s instanceof JailSquare) {
                JailSquare jail = (JailSquare) s;
                Board.getInstance().setJail(jail);
            }
            squares.put(s.getNumberSquare(), s);
        });
        List<PlayerImplement> players = Board.getInstance().getPlayers().stream().toList();
        Board.getInstance().setSquares(this.squares);
        Board.getInstance().setNumbersOfSquare(this.squares.size()-1);
        players.forEach(p->p.setSquare(Board.getInstance().getSquare(0)));

    }

    @Override
    public void addProperties() {
        infoGameDto.getProperties().forEach(p -> properties.put(p.getPropertyId().intValue(), p));
        Board.getInstance().setProperties(this.properties);

    }


    @Override
    public Game getGame() {
        this.gameId = infoGameDto.getGameId();
        if(infoGameDto.getVictoryAmount() != null){
            this.victoryAmount = infoGameDto.getVictoryAmount();
        }
        Board.getInstance().setGameId(gameId);
        return new Game(this.gameId, this.difficulty, this.victoryAmount, this.turn, this.players, this.initialBalance);
    }
}


