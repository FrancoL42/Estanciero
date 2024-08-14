package ar.edu.utn.frc.tup.lciii.model.player.strategy;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.AgressiveBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.ConservativeBotStrategy;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.CompanyProperty;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.FreePropertyState;
import org.hibernate.mapping.Property;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ConservativePlayerStrategyTest {
    private ConservativeBotStrategy playerStrategy ;

    private PlayerImplement playerImplement;

    private AbstractProperty property;

    private List<Card> card;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        playerStrategy = new ConservativeBotStrategy();
        property = FieldProperty.builder()
                .province("Rio Negro")
                .farmValue(700)
                .farmCount(0)
                .build();
        property.setPropertyValue(700);

    }
    @Test
    public void TestBuyPropertyField(){
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        property.setState(new FreePropertyState());
        playerStrategy.buyProperty(playerImplement,property);
        assertSame(playerImplement, property.getOwner());
        assertEquals(playerImplement.getBalance(),5300);;
    }
    @Test
    public void TestBuyPropertyCompany(){
        playerImplement = new PlayerImplement();
        playerImplement.setBalance(6000);
        playerImplement.setProperties(new ArrayList<>());
        CompanyProperty companyProperty = new CompanyProperty();
        companyProperty.setPropertyValue(1000);
        companyProperty.setState(new FreePropertyState());
        playerStrategy.buyProperty(playerImplement,companyProperty);
        assertEquals(0, playerImplement.getProperties().size());
        assertNull(property.getOwner());
        assertEquals(playerImplement.getBalance(),6000);;
    }
//    @Test
//    public void TestBuyFarm(){
//        //strategy.buyFarm(property,playerImplement);
//    }
//    @Test
//    public void TestMortGageProperty(){
//        strategy.mortGageProperty(abstractProperties,playerImplement);
//    }
//    @Test
//    public void TestUseCard(){
//        strategy.useCard(cards,playerImplement);
//        when(cards.get(0)).thenThrow(new IndexOutOfBoundsException());
//    }
//    @Test
//    public void TestSellFarm(){
//        strategy.sellFarm(playerImplement);
//    }
}
