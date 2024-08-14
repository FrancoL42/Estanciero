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
@Table(name="GAMES_PROPERTIES")
@Entity

public class GamePropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private Integer farmCount=0;
    @Column
    private Boolean isMortgaged = false;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;
    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;


}
