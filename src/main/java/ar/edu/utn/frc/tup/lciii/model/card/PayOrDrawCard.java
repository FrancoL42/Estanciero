package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import ar.edu.utn.frc.tup.lciii.model.console.Console;
import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.player.strategies.HumanPlayerStrategy;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayOrDrawCard extends AbstractCard {
    private int penalty;
    Console console = new Console();

    @Override
    public void executeCard(PlayerImplement player) {
        LetterByLetterPrinter.println(this.cardDescription);
        if (player.getStrategy() instanceof HumanPlayerStrategy) {
            int option = 0;
            do {
                LetterByLetterPrinter.println("¿desea pagar $" + penalty + " o sacar una carta de suerte?(1/2)");
                option = console.inputInt();
            } while (!(option == 1 || option == 2));
            if (option == 1) {
                LetterByLetterPrinter.println(player.getPlayerName() + " debe pagar $" + penalty);
                player.updateBalance(penalty);
            } else {
                LetterByLetterPrinter.println("Obtuvo esta carta de Suerte:");
                Card c = Board.getInstance().getChanceCard();
                c.executeCard(player);
            }
            addCardToBoard();
        } else {
            player.updateBalance(penalty);
        }

    }
}
