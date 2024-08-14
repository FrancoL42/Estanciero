package ar.edu.utn.frc.tup.lciii.model.square;

import ar.edu.utn.frc.tup.lciii.model.console.LetterByLetterPrinter;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.property.Property;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PropertySquare extends AbstractSquare{

    Property property;
    @Override
    public void executeSquare(PlayerImplement player) {
        LetterByLetterPrinter.println(player.getPlayerName()
                +" cayó en la propiedad "+((AbstractProperty)property).getName());
        property.executeState(player);
    }
}
