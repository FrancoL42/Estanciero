package ar.edu.utn.frc.tup.lciii.entity;

import ar.edu.utn.frc.tup.lciii.model.card.*;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CARDS")
@Entity
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private Integer displacement;
    @Column
    private Integer moveToSquare;
    @Column
    private Integer amount;
    @ManyToOne
    @JoinColumn(name = "cardCalssification_id")
    private CardCalssificationEntity cardCalssification;
    @ManyToOne
    @JoinColumn(name = "cardType_id")
    private CardTypeEntity cardType;

    public Card cardTransformer(CardEntity cardEntity){
        //TODO cambiar el switch si cambian las cards
        AbstractCard card = null;
        if(cardEntity.getCardType().getDescription().equals("Suerte")){
            switch (cardEntity.cardCalssification.getClassification()){
                case "Sal de la Carcel":
                    card = new OutOfJailCard();
                    break;
                case "Cobrar o pagar":
                    card = new PrizePenaltyCard();
                    break;
                case "Cobre de todos":
                    card = new PrizePerPlayerCard();
                    break;
                case "Desplazarse a Casilla":
                    card = new DisplacementCard();
                    break;
                case "Avanzar retroceder":
                    card = new GoToSquareCard();
                    break;
                case "Pago por chacra":
                    card = new FarmPenaltyCard();
                    break;
                case "Pague o levante":
                    card = new PrizePenaltyCard();
                    break;
            }
        }
        if(cardEntity.getCardType().getDescription().equals("Destino")){
            switch (cardEntity.cardCalssification.getClassification()){
                case "Sal de la Carcel":
                    card = new OutOfJailCard();
                    break;
                case "Cobrar o pagar":
                    card = new PrizePenaltyCard();
                    break;
                case "Cobre de todos":
                    card = new PrizePerPlayerCard();
                    break;
                case "Desplazarse a Casilla":
                    card = new DisplacementCard();
                    break;
                case "Avanzar retroceder":
                    card = new GoToSquareCard();
                    break;
                case "Pago por chacra":
                    card = new FarmPenaltyCard();
                    break;
                case "Pague o levante":
                    card = new PrizePenaltyCard();
                    break;
            }
        }


        return card;
    }
}
