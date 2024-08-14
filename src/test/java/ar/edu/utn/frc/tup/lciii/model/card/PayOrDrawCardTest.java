package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PayOrDrawCardTest {


    private PayOrDrawCard payOrDrawCard;

    @Mock
    private Console console;
    private PlayerImplement playerImplement;

    private Board board;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        payOrDrawCard = new PayOrDrawCard();
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(5000);
        payOrDrawCard.setCardDescription("Debe pagar");
        payOrDrawCard.setConsole(console);
        payOrDrawCard.setPenalty(-1000);
        board = Board.getInstance();
    }

    @Test
    public void executeCardTest(){
        //TEST SIENDO UN BOT
        playerImplement.setStrategy(new AgressiveBotStrategy());
        payOrDrawCard.executeCard(playerImplement);
        assertEquals(4000, playerImplement.getBalance());
    }

    @Test
    public void executeCardTestHuman(){
        playerImplement.setStrategy(new HumanPlayerStrategy());
        playerImplement.setPlayerName("Pep ");
        when(console.inputInt()).thenReturn(2);
        FarmPenaltyCard farmPenaltyCard = new FarmPenaltyCard();
        farmPenaltyCard.setValuePenalty(5000);
        farmPenaltyCard.setCardType(1L);
        AbstractCard card = farmPenaltyCard;
        card.setCardDescription("Hoy no vinieron los municipales, no pagas nada!");
        board.setChanceCard(card);
        payOrDrawCard.setCardType(1L);
        payOrDrawCard.executeCard(playerImplement);
        assertEquals(5000, playerImplement.getBalance());
    }
}
