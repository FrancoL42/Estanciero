package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.entity.CardEntity;
import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.*;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.service.CardService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class DestinySquareTest {
    private DestinySquare destinySquare;




    @BeforeEach
    public void createSquare(){
        MockitoAnnotations.openMocks(this);
        destinySquare = new DestinySquare();
    }

    @Test

    public void executeSquareTest0(){
        Board.getInstance().clear();
        OutOfJailCard card1 = new OutOfJailCard();
        card1.setCardType(2L); //1 es lucky
        card1.setCardDescription("Salir de la carcel");
        card1.setOrder(1);

        PrizePenaltyCard card2 = new PrizePenaltyCard();
        card2.setCardType(2L);
        card2.setPrizePenaltyAmount(500);
        card2.setCardDescription("Salir de la carcel");
        card2.setOrder(2);

        Board.getInstance().setDestinyCard(card1);
        Board.getInstance().setDestinyCard(card2);

        PlayerImplement playerImplement1 = new PlayerImplement();
        playerImplement1.setPlayerName("pepe");
        playerImplement1.setBalance(50);
        playerImplement1.setCards(new LinkedList<>());
        playerImplement1.setStrategy(new ConservativeBotStrategy());
        AbstractSquare square = new DestinySquare();
        square.setSquareId(1L);
        square.executeSquare(playerImplement1);
        square.executeSquare(playerImplement1);

        assertEquals(550, playerImplement1.getBalance() );
        assertEquals(Board.getInstance().getDestinyCards().size(),1);
    }

}

