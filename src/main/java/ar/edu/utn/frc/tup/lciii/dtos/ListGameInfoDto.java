package ar.edu.utn.frc.tup.lciii.dtos;

import ar.edu.utn.frc.tup.lciii.model.card.AbstractCard;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import ar.edu.utn.frc.tup.lciii.model.player.Player;
import ar.edu.utn.frc.tup.lciii.model.player.PlayerImplement;
import ar.edu.utn.frc.tup.lciii.model.property.AbstractProperty;
import ar.edu.utn.frc.tup.lciii.model.square.Square;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Properties;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListGameInfoDto {
    private List<Square> squares;
    private List<AbstractCard> cardsDestiny;
    private List<AbstractCard> cardsChance;
    private List<PlayerImplement> players;
    private List<AbstractProperty> properties;
    private Long gameId;
    private Integer victoryAmount;
}
