package ar.edu.utn.frc.tup.lciii.entity.converts;

import ar.edu.utn.frc.tup.lciii.entity.CardGameEntity;
import ar.edu.utn.frc.tup.lciii.model.card.*;

public class CardsConvert {
    public static AbstractCard convertToCard(CardGameEntity cardGame) {
        AbstractCard card = null;
        Long cardTypeId = cardGame.getCard().getCardCalssification().getId();
        if (cardTypeId == 1L) {
            card = new GoToSquareCard();
            ((GoToSquareCard)card).setNumberSquare(cardGame.getCard().getMoveToSquare());
        } else if (cardTypeId == 2L) {
            card = new OutOfJailCard();
        } else if (cardTypeId == 3L) {
            card = new PrizePenaltyCard();
            ((PrizePenaltyCard)card).setPrizePenaltyAmount(cardGame.getCard().getAmount());
        } else if (cardTypeId == 4L) {
            card = new PrizePerPlayerCard();
            ((PrizePerPlayerCard)card).setPrizePerPlayerAmount(cardGame.getCard().getAmount());
        } else if (cardTypeId == 5L) {
            card = new GoToSquareCard();
            ((GoToSquareCard)card).setNumberSquare(cardGame.getCard().getMoveToSquare());
        } else if (cardTypeId == 6L) {
            card = new DisplacementCard();
            ((DisplacementCard)card).setDisplacement(cardGame.getCard().getDisplacement());
        } else if (cardTypeId == 7L) {
            card = new FarmPenaltyCard();
            ((FarmPenaltyCard)card).setValuePenalty(cardGame.getCard().getAmount());
        } else if (cardTypeId == 8L) {
            card = new PayOrDrawCard();
            ((PayOrDrawCard)card).setPenalty(cardGame.getCard().getAmount());
        }
        card.setCardId(cardGame.getId());
        card.setCardDescription(cardGame.getCard().getDescription());
        card.setOrder(cardGame.getCardOrder());
        card.setCardType(cardGame.getCard().getCardType().getId());
        card.setCardConfigId(cardGame.getCard().getId());
        return card;
    }


    public static CardGameEntity convertToCardGameEntity(AbstractCard card){
        CardGameEntity cardGame = new CardGameEntity();
        cardGame.setId(card.getCardId());
        cardGame.setCardOrder(card.getOrder());
        return cardGame;
    }
}
