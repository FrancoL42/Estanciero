package ar.edu.utn.frc.tup.lciii.model.board;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import ar.edu.utn.frc.tup.lciii.model.square.JailSquare;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import ar.edu.utn.frc.tup.lciii.service.GameService;
import ar.edu.utn.frc.tup.lciii.service.PlayerService;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.GameServiceImpl;
import ar.edu.utn.frc.tup.lciii.service.impl.PlayerServiceImpl;
import lombok.Getter;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.LinkedTransferQueue;

@Setter
@Getter
public class Board {

    private Integer numbersOfSquare;
    GameService gameService = new GameServiceImpl();
    private CardService cardService = new CardServiceImpl();
    private PlayerService playerService = new PlayerServiceImpl();
    private Long gameId;
    private JailSquare Jail;
    private Map<Integer, AbstractProperty> properties;
    private Map<Integer,Square> squares = new HashMap();
    private Queue<Card> chanceCards = new ArrayDeque<>();
    private Queue<Card> destinyCards = new ArrayDeque<>();

    private Board (){
    }

    private static class BoardHelper {
        private static final Board INSTANCE = new Board();

    }

    public static Board getInstance(){
        return BoardHelper.INSTANCE;
    }

    private Queue<PlayerImplement> players = new ArrayDeque<>();
    public void setearPlayers(Long gameid){
//        List<PlayerImplement> playerList = playerService.getAllPlayer(gameid);
//        players.addAll(playerList);
    }
    public PlayerImplement getLastPlayers() {

        if(players.isEmpty()){
            return null;
        }
        return players.poll();
    }

    public void setPlayer(PlayerImplement player){
        players.add(player);
    }

    public Square getSquare(Integer numberSquare){
        //Buscar la casilla a traves de su nunero de casilla (key del map)
        return squares.get(numberSquare);
    };

    public Card getChanceCard (){
        if(chanceCards.isEmpty()){
            return null;
        }
        return chanceCards.poll();
    }

    public void setChanceCard(Card card){
        chanceCards.add(card);
    }

    public void prepareCards(){
        chanceCards.addAll(cardService.getAllChanceCards());
        destinyCards.addAll(cardService.getAllDestinyCards());
    }
    public Card getdestinyCard (){
        if(destinyCards.isEmpty()){
            return null;
        }
        return destinyCards.poll();
    }

    public void setDestinyCard(Card card){
        //index 0 porque sería como dejarla más abajo en la vida real
        destinyCards.add(card);
    }

    public Property getProperty(Integer numberSquare){
        //Buscar por casilla a la que pertenece la propiedad
        return properties.get(numberSquare);
    }

    public void clear(){
        this.players = new LinkedTransferQueue<>();
        this.properties=new HashMap<>();
        this.chanceCards = new ArrayDeque<>() {
        };
        this.squares = new HashMap<>();
        this.destinyCards= new ArrayDeque<>();
    }

    public void save(){
        List<PlayerImplement> playerList =new ArrayList<>();
        Integer count = 1;
        Deque<PlayerImplement> playersCopy = new LinkedList<>(players);
        while (!playersCopy.isEmpty()){
            PlayerImplement player = playersCopy.poll();
            player.setOrderTurn(count);
            count++;
            playerList.add(player);
        }
        List<AbstractProperty> propertyList = properties.values().stream().toList();
        List<AbstractCard> cardList = new ArrayList<>();
        Deque<Card> cardDestinyCopy = new LinkedList<>(destinyCards);
        count=1;
        while (!cardDestinyCopy.isEmpty()){
            AbstractCard card = (AbstractCard) cardDestinyCopy.poll();
            card.setOrder(count);
            count++;
            cardList.add(card);
        }
        count=1;
        Deque<Card> cardChanceCopy = new LinkedList<>(chanceCards);

        while (!cardChanceCopy.isEmpty()){
            AbstractCard card = (AbstractCard) cardChanceCopy.poll();
            card.setOrder(count);
            count++;
            cardList.add(card);
        }
        gameService.save(gameId,propertyList, cardList, playerList);

    }


}

