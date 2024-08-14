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
@Table(name="PLAYERS")
@Entity

public class PlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(name = "balance")
    private Integer balance=5000;
    @Column
    private Integer numberDoubleDiceThrown;
    @Column (name="restCount")
    private Integer restCount;
    @Column(name = "inJail")
    private Boolean inJail;
    @Column(name = "orderTurn")
    private Integer orderTurn;
    @ManyToOne
    @JoinColumn(name ="position_id")
    private SquareEntity square;
    @ManyToOne
    @JoinColumn(name = "token_id")
    private TokenEntity token;
    @ManyToOne
    @JoinColumn(name="playerType_id")
    private PlayerTypeEntity playerType;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    @ManyToOne
    @JoinColumn(name = "game_id")
    private GameEntity game;

}
