package ar.edu.utn.frc.tup.lciii.model.square;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public abstract class AbstractSquare implements Square {
    protected  Long squareId;

    protected Integer numberSquare;
}
