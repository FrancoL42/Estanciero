package ar.edu.utn.frc.tup.lciii.model.dice;

import lombok.Getter;
import lombok.Setter;

import java.util.Random;
@Getter
@Setter
public class DiceImpl implements Dice{
    //Guarda ultimo resulado
    private DiceResult result;
    private DiceImpl (){
    }

    private static class DiceImplHelper {
        private static final DiceImpl INSTANCE = new DiceImpl();

    }

    public static DiceImpl getInstance(){
        return DiceImpl.DiceImplHelper.INSTANCE;
    }

    @Override
    public DiceResult rollDice() {

        Random random = new Random();

        int diceOne = random.nextInt(6) + 1;
        int diceTwo = random.nextInt(6)+1;

        result = new DiceResult();

        result.setIsMirror(diceOne==diceTwo);
        result.setSumDices(diceTwo+diceOne);
        
        return result;
    }
}
