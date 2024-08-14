package ar.edu.utn.frc.tup.lciii.model.card;

import ar.edu.utn.frc.tup.lciii.model.board.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractCard implements Card {
    protected Long cardId;
    protected Long cardType;
    protected Long cardConfigId;
    protected String cardDescription;
    protected Integer order;

    @Override
    public String toString(){
        return cardDescription;
    }

    public void addCardToBoard(){
        if(cardType == 1)
            Board.getInstance().setChanceCard(this);
        else
            Board.getInstance().setDestinyCard(this);
    }
}
