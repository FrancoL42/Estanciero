package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.FieldProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FarmPenaltyCard extends AbstractCard {
    private Integer valuePenalty;

    @Override
    public void executeCard(PlayerImplement p) {
        LetterByLetterPrinter.println(this.cardDescription);
        int acumulator = 0;
        for (Property property : p.getProperties()) {
            if (property instanceof FieldProperty) {
                int cantidad = ((FieldProperty) property).getFarmCount();
                acumulator += cantidad;
                if (cantidad > 0) {
                    if (cantidad < 5) {
                        LetterByLetterPrinter.println(p.getPlayerName() + " tiene " + cantidad
                                + " chacras en " + ((FieldProperty) property).getName());
                    } else
                        LetterByLetterPrinter.println(p.getPlayerName() + " tiene estancia en "
                                + ((FieldProperty) property).getName());
                }
            }
        }
        int total = acumulator * valuePenalty;
        LetterByLetterPrinter.println(p.getPlayerName() + " debe pagar " + total);
        addCardToBoard();
        p.updateBalance(total);
    }
}