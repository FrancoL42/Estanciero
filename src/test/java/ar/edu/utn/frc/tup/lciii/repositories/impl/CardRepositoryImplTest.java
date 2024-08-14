package ar.edu.utn.frc.tup.lciii.repositories.impl;

import ar.edu.utn.frc.tup.lciii.entity.CardEntity;
import ar.edu.utn.frc.tup.lciii.entity.CardGameEntity;
import ar.edu.utn.frc.tup.lciii.repositories.CardRepository;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CardRepositoryImplTest {
    CardRepository cardRepository = new CardRepositoryImpl();
    @Test
    public void getChanceCardsByGameTest(){
        Long id = Long.parseLong("1");
        List<CardGameEntity> cardEntityList = cardRepository.getChanceCardsByGameId(id);
        assertNotNull(cardEntityList);
    }
    @Test
    public void getDestinyCardsByGameIdTest(){
        Long id = Long.parseLong("1");
        List<CardGameEntity> cardEntityList = cardRepository.getDestinyCardsByGameId(id);
        assertNotNull(cardEntityList);
    }
    @Test
    public void getCardsTest(){
        Long id = Long.parseLong("1");
        List<CardEntity> cardEntityList = cardRepository.getCards("'Destino'");
        assertNotNull(cardEntityList);
    }
    @Test
    public void getCardsTestFail(){
        List<CardEntity> cardEntityList = cardRepository.getCards("'Destinos");
        assertNull(cardEntityList);
    }
    @Test
    public void getCardsTest2(){
        Long id = Long.parseLong("1");
        List<CardGameEntity> cardEntityList = cardRepository.getCards("'Destino'",id);
        assertNotNull(cardEntityList);
    }
    @Test
    public void getCardsTest2Fail(){
        Long id = Long.parseLong("1");
        List<CardGameEntity> cardEntityList = cardRepository.getCards("'Destino",id);
        assertNull(cardEntityList);
    }

    @Test
    public void getByIdTest(){
        Long id = 1L;
        CardEntity card = new CardEntity();
        CardEntity result = cardRepository.getById(id);
        card.setId(id);
        assertNotNull(result);
        assertEquals(card.getId(), result.getId());
    }

}
