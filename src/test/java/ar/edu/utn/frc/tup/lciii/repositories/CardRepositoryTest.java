package ar.edu.utn.frc.tup.lciii.repositories;

import ar.edu.utn.frc.tup.lciii.entity.CardEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CardRepositoryTest {

    @Mock
    private CardRepository cardRepository;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void TestGetAllLuckyCards(){
        List<CardEntity> luckyCards = new ArrayList<>();
        luckyCards.add(new CardEntity());
        luckyCards.add(new CardEntity());

        when(cardRepository.getChanceCards()).thenReturn(luckyCards);
        List<CardEntity> result = cardRepository.getChanceCards();
        assertEquals(luckyCards,result);
    }
    @Test
    void testGetAllDestinyCards() {

        List<CardEntity> destinyCards = new ArrayList<>();
        destinyCards.add(new CardEntity());
        destinyCards.add(new CardEntity());

        when(cardRepository.getDestinyCards()).thenReturn(destinyCards);
        List<CardEntity> result = cardRepository.getDestinyCards();
        assertEquals(destinyCards, result);
    }
}
