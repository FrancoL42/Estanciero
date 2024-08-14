package ar.edu.utn.frc.tup.lciii.entity.converts;

import ar.edu.utn.frc.tup.lciii.entity.CardCalssificationEntity;
import ar.edu.utn.frc.tup.lciii.entity.CardEntity;
import ar.edu.utn.frc.tup.lciii.entity.CardGameEntity;
import ar.edu.utn.frc.tup.lciii.entity.CardTypeEntity;
import ar.edu.utn.frc.tup.lciii.model.card.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CardsConvertTest {
    private AbstractCard testCard;
    private CardGameEntity cardGame;
    private CardEntity card;
    private CardTypeEntity cardType;

    private CardCalssificationEntity cardCalssificationEntity;



    @BeforeEach
    public void setUp(){
        testCard = new OutOfJailCard();
        testCard.setCardId(123L);
        testCard.setOrder(1);
        testCard.setCardDescription("This is a test card");
        testCard.setCardType(4L);
        testCard.setCardConfigId(456L);

        cardGame = new CardGameEntity();
        card = new CardEntity();
        cardType = new CardTypeEntity();

        cardGame.setCard(card);
        cardGame.setCardOrder(1);
        cardGame.setId(100L);
        card.setCardType(cardType);
    }

    @Test
    public void convertToCardGameEntity(){
        CardGameEntity cardGame = CardsConvert.convertToCardGameEntity(testCard);

        assertNotNull(cardGame);

        assertEquals(123L,cardGame.getId());
        assertEquals(1, cardGame.getCardOrder());
    }

    @Test
    public void convertToCardType1(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(2L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(1L);
        card.setDescription("Get out of jail card");
        card.setId(456L);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof OutOfJailCard);
        assertEquals("Get out of jail card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(1L, convertedCard.getCardType());
        assertEquals(456L, convertedCard.getCardConfigId());
    }

    @Test
    public void convertToCardType2(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(2L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(2L);
        card.setDescription("Go to jail card");
        card.setId(459L);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof OutOfJailCard);
        assertEquals("Go to jail card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(2L, convertedCard.getCardType());
        assertEquals(459L, convertedCard.getCardConfigId());
    }

    @Test
    public void convertToCardType3(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(3L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(3L);
        card.setDescription("Prize or penalty card");
        card.setId(457L);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof PrizePenaltyCard);
        assertEquals("Prize or penalty card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(3L, convertedCard.getCardType());
        assertEquals(457L, convertedCard.getCardConfigId());
    }

    @Test
    public void convertToCardType4(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(4L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(4L);
        card.setDescription("Prize per player card");
        card.setId(460L);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof PrizePerPlayerCard);
        assertEquals("Prize per player card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(4L, convertedCard.getCardType());
        assertEquals(460L, convertedCard.getCardConfigId());
    }

    @Test
    public void convertToCardType5(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(5L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(5L);
        card.setDescription("Go to square card");
        card.setId(458L);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof GoToSquareCard);
        assertEquals("Go to square card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(5L, convertedCard.getCardType());
        assertEquals(458L, convertedCard.getCardConfigId());
    }



    @Test
    public void convertToCardType6(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(6L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(6L);
        card.setDescription("Displacement card");
        card.setId(461L);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof DisplacementCard);
        assertEquals("Displacement card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(6L, convertedCard.getCardType());
        assertEquals(461L, convertedCard.getCardConfigId());
    }

    @Test
    public void convertToCardType7(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(7L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(7L);
        card.setDescription("Farm penalty card");
        card.setId(462L);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof FarmPenaltyCard);
        assertEquals("Farm penalty card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(7L, convertedCard.getCardType());
        assertEquals(462L, convertedCard.getCardConfigId());
    }

    @Test
    public void convertToCardType8(){
        CardCalssificationEntity cardCalssificationEntity = new CardCalssificationEntity();
        cardCalssificationEntity.setId(8L);
        card.setCardCalssification(cardCalssificationEntity);
        cardType.setId(8L);
        card.setDescription("Pay or draw card");
        card.setId(463L);
        card.setAmount(9);

        AbstractCard convertedCard = CardsConvert.convertToCard(cardGame);

        assertTrue(convertedCard instanceof PayOrDrawCard);
        assertEquals("Pay or draw card", convertedCard.getCardDescription());
        assertEquals(1, convertedCard.getOrder());
        assertEquals(8L, convertedCard.getCardType());
        assertEquals(463L, convertedCard.getCardConfigId());
    }

}
