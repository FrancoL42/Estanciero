package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import lombok.Getter;
import lombok.Setter;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Getter
@Setter
public class PrizePerPlayerCard extends AbstractCard {

    private Integer prizePerPlayerAmount;

    @Override
    public void executeCard(PlayerImplement p) {
        LetterByLetterPrinter.println(this.cardDescription);
        int playersRemaining = Board.getInstance().getPlayers().stream().filter(player -> !player.isLoser()).toList().size();
        int total = prizePerPlayerAmount * playersRemaining;
        LetterByLetterPrinter.println(p.getPlayerName() + " obtiene " + total + " del resto de los jugadores.");
        p.updateBalance(total);
        addCardToBoard();
        for (PlayerImplement player : Board.getInstance().getPlayers()) {
            if (player != p) {
                player.updateBalance(-prizePerPlayerAmount);
            }
        }
    }
}
