package ar.edu.utn.frc.tup.lciii.model.property.state;

import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.states.MortgagePropertyState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class MortgagePropertyStateTest {
    private MortgagePropertyState mortgage;
    private AbstractProperty abstractProperty;
    private PlayerImplement playerImplement;

    @BeforeEach
    public  void setUp(){
        mortgage = new MortgagePropertyState();
        abstractProperty = Mockito.mock(AbstractProperty.class);
        playerImplement = Mockito.mock(PlayerImplement.class);
    }
    @Test
    public void TestNextState(){
        mortgage.nextState(abstractProperty);
    }
    @Test
    public void TestExecuteState(){
        mortgage.executeState(playerImplement,abstractProperty);
    }
}
