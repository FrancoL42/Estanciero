package ar.edu.utn.frc.tup.lciii.model.board;

import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.card.DisplacementCard;
import ar.edu.utn.frc.tup.lciii.model.card.GoToSquareCard;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    Board board = Board.getInstance();

    @Disabled
    @Test
    public void setAndGetPlayerTest(){
        PlayerImplement player1 = new PlayerImplement();
        PlayerImplement player2 = new PlayerImplement();

        board.setPlayer(player1);
        board.setPlayer(player2);

        Player playerGet1= board.getLastPlayers();
        Player playerGet2= board.getLastPlayers();

        Player playerNull =board.getLastPlayers();

        assertEquals(player1,playerGet2);
        assertEquals(player2, playerGet1);
        assertNull(playerNull);

    }

    @Disabled
    @Test
    public void chanceCardTest(){
        Card card1 = new GoToSquareCard();
        Card card2 = new DisplacementCard();

        board.setChanceCard(card1);
        board.setChanceCard(card2);

        Card cardGet1 = board.getChanceCard();
        Card cardGet2 = board.getChanceCard();
        Card cardGetNull = board.getChanceCard();

        assertEquals(card1,cardGet1);
        assertEquals(card2, cardGet2);
        assertNull(cardGetNull);
    }
    @Disabled
    //TODO
    @Test
    public void destinyCardTest(){
        Card card1 = new GoToSquareCard();
        Card card2 = new DisplacementCard();

        board.setDestinyCard(card1);
        board.setDestinyCard(card2);

        Card cardGet1 = board.getdestinyCard();
        Card cardGet2 = board.getdestinyCard();
        Card cardGetNull = board.getdestinyCard();

        assertSame(card1,cardGet2);
        assertSame(card2, cardGet1);
        assertNull(cardGetNull);
    }

    @Test
    public void squareTest(){
        //TODO: falta terminar este test, controlar despues de finalizar la clase Board;

        board.getSquare(1);

    }

    @Test
    public void propertyTest(){
        //TODO: falta terminar este test, controlar despues de finalizar la clase Board;

       // board.getProperty(1);

    }

}
