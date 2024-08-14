package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.card.OutOfJailCard;
import ar.edu.utn.frc.tup.lciii.model.card.PrizePenaltyCard;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.MockitoAnnotations;

import java.util.LinkedList;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

public class LuckySquareTest {

    @Mock
    private PlayerImplement playerImplement;



    @BeforeEach
    public void createSquare(){
        MockitoAnnotations.openMocks(this);

    }

    @Test
    public void executeSquareTest(){
        Board.getInstance().clear();

        OutOfJailCard card1 = new OutOfJailCard();
        card1.setCardType(1L); //1 es lucky
        card1.setCardDescription("Salir de la carcel");
        card1.setOrder(1);

        PrizePenaltyCard card2 = new PrizePenaltyCard();
        card2.setCardType(1L);
        card2.setPrizePenaltyAmount(500);
        card2.setCardDescription("Salir de la carcel");
        card2.setOrder(2);

        Board.getInstance().setChanceCard(card1);
        Board.getInstance().setChanceCard(card2);

        PlayerImplement playerImplement1 = new PlayerImplement();
        playerImplement1.setPlayerName("pepe");
        playerImplement1.setBalance(50);
        playerImplement1.setCards(new LinkedList<>());
        playerImplement1.setStrategy(new ConservativeBotStrategy());
        AbstractSquare square = new LuckSquare();
        square.setSquareId(1L);
        square.executeSquare(playerImplement1);
        square.executeSquare(playerImplement1);

        assertEquals(550, playerImplement1.getBalance() );
        assertEquals(Board.getInstance().getChanceCards().size(),1);
    }
    @Test
    public void testExecuteSquareWithoutCard() {

//        Card card = mock(Card.class);
//        when(board.getChanceCard()).thenReturn(null);
//        LuckSquare luckSquare = new LuckSquare(1L, 2);
//
//        luckSquare.executeSquare(playerImplement);
//
//    //    verify(playerImplement).setSquare(luckSquare);
//        verifyNoInteractions(card);
    }
}
