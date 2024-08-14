package ar.edu.utn.frc.tup.lciii.services;

import ar.edu.utn.frc.tup.lciii.entity.*;
import ar.edu.utn.frc.tup.lciii.entity.converts.CardsConvert;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.card.GoToSquareCard;
import ar.edu.utn.frc.tup.lciii.model.console.User;
import ar.edu.utn.frc.tup.lciii.model.game.Game;
import ar.edu.utn.frc.tup.lciii.model.game.builder.BuilderDirector;
import ar.edu.utn.frc.tup.lciii.model.game.builder.EasyGameBuilder;
import ar.edu.utn.frc.tup.lciii.model.game.builder.HardGameBuilder;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.repositories.CardRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.CardRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import ar.edu.utn.frc.tup.lciii.service.impl.CardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class CardServiceTest {

   private CardService cardService ;


    @BeforeEach
    void setUp(){
        cardService = new CardServiceImpl();
    }
    @Test
    public void getAllChanceCards(){
        List<Card> cards = cardService.getAllChanceCards();
        assertNotEquals(Collections.EMPTY_LIST,cards);

    }
    @Test
    public void getAllDestinyCards(){
        List<Card> cards = cardService.getAllDestinyCards();
        assertNotEquals(Collections.EMPTY_LIST,cards);
    }
    @Disabled
    @Test
    public void getAllDestiniCardsByGame(){
        User user = new User(1L,"user");
        HardGameBuilder easyGameBuilder = new HardGameBuilder(user,25000,50000);
        BuilderDirector director = new BuilderDirector();
        Game game = easyGameBuilder.getGame();
        director.NewGame(easyGameBuilder);
        Board board = Board.getInstance();
        Board.getInstance().save();
        Board.getInstance().getProperty(6);
        List<AbstractCard> result =  cardService.getAllDestinyCardsByGame(game.getGameId(), board.getPlayers().stream().toList());
        assertNotNull(result);

        PlayerImplement player = board.getPlayers().poll();

        for (AbstractCard card1 : result) {
            if (card1.getCardId().equals(1L)) {
                assertTrue(player.getCards().contains(result));
            }
        }
        List<AbstractCard> result1 =  cardService.getAllChanceCardsByGame(game.getGameId(), board.getPlayers().stream().toList());
        assertNotNull(result1);

        PlayerImplement player2 = board.getPlayers().poll();

        for (AbstractCard card1 : result) {
            if (card1.getCardId().equals(1L)) {
                assertFalse(player2.getCards().contains(result1));
            }
        }
    }




}
