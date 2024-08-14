package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
@Getter
@Setter
public class PrizePenaltyCard extends AbstractCard{

    private Integer prizePenaltyAmount;
    @Override
    public void executeCard(PlayerImplement p) {

        LetterByLetterPrinter.println(this.cardDescription);
        if(prizePenaltyAmount<0)
            LetterByLetterPrinter.println(p.getPlayerName()+ " debe pagar $"+prizePenaltyAmount);
        else
            LetterByLetterPrinter.println(p.getPlayerName()+" obtiene "+prizePenaltyAmount);
        addCardToBoard();
        p.updateBalance(prizePenaltyAmount);
    }
}
