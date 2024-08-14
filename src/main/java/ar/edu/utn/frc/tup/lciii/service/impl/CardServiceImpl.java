package ar.edu.utn.frc.tup.lciii.service.impl;

import ar.edu.utn.frc.tup.lciii.entity.CardEntity;
import ar.edu.utn.frc.tup.lciii.entity.CardGameEntity;
import ar.edu.utn.frc.tup.lciii.entity.PlayerEntity;
import ar.edu.utn.frc.tup.lciii.entity.converts.CardsConvert;
import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.repositories.CardRepository;
import ar.edu.utn.frc.tup.lciii.repositories.impl.CardRepositoryImpl;
import ar.edu.utn.frc.tup.lciii.service.CardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardServiceImpl implements CardService {
    CardRepository cardRepository = new CardRepositoryImpl();
    CardEntity cardEntity = new CardEntity();
    @Override
    public List<Card> getAllChanceCards() {
        List<CardEntity> cardEntities = cardRepository.getChanceCards();
        List<Card> cards = new ArrayList<>();
        for(CardEntity entity : cardEntities){
            Card card = cardEntity.cardTransformer(entity);
            cards.add(card);
        }
        return cards;
    }

    @Override
    public List<Card>  getAllDestinyCards() {
        List<CardEntity> cardEntities = cardRepository.getDestinyCards();
        List<Card> cards = new ArrayList<>();
        for(CardEntity entity : cardEntities){
            Card card = cardEntity.cardTransformer(entity);
            cards.add(card);
        }
        return cards;
    }

    @Override
    public List<AbstractCard> getAllChanceCardsByGame(Long gameId, List<PlayerImplement> players) {
      List<CardGameEntity> cardsChance =  cardRepository.getChanceCardsByGameId(gameId);
      List<AbstractCard> cards = new ArrayList<>();
        for (CardGameEntity cardGame : cardsChance) {
            AbstractCard abstractCard = CardsConvert.convertToCard(cardGame);
            if(cardGame.getPlayer() != null){
                Optional<PlayerImplement> playerImplement =  players.stream()
                        .filter(p -> p.getPlayerID().equals(cardGame.getPlayer().getId()))
                        .findAny();
                if(playerImplement.isPresent()){
                    playerImplement.get().getCards().add(abstractCard);
                }
            } else {
                cards.add(abstractCard);
            }
        }
        return cards;
    }

    @Override
    public List<AbstractCard> getAllDestinyCardsByGame(Long gameId, List<PlayerImplement> players) {
        List<CardGameEntity> cardsDestiny =  cardRepository.getDestinyCardsByGameId(gameId);
        List<AbstractCard> cards = new ArrayList<>();
        for (CardGameEntity cardGame : cardsDestiny) {
            AbstractCard abstractCard = CardsConvert.convertToCard(cardGame);
            if(cardGame.getPlayer() != null){
                Optional<PlayerImplement> playerImplement =  players.stream()
                        .filter(p -> p.getPlayerID().equals(cardGame.getPlayer().getId()))
                        .findAny();
                playerImplement.get().getCards().add(abstractCard);
            } else {
                cards.add(abstractCard);
            }

        }
        return cards;
    }

}
