package ar.edu.utn.frc.tup.lciii.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="CARD_GAME")
@Entity

public class CardGameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer cardOrder;
    @Column
    private Boolean isLastDrawn;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private CardEntity card;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

}
