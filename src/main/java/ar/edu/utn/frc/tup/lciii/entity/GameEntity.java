package ar.edu.utn.frc.tup.lciii.entity;
import ar.edu.utn.frc.tup.lciii.model.card.Card;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="GAMES")
@Entity
@Data
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "initial_balance")
    private Integer initialBalance;
    @Column(name = "VICTORY_AMOUNT")
    private Integer victoryAmount;
    @ManyToOne
    @JoinColumn(name = "user_Id")
    @Enumerated(value = EnumType.STRING)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "difficulty_id")
    @Enumerated(value = EnumType.STRING)
    private DifficultyEntity difficulty;
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<GamePropertyEntity> properties = new ArrayList<>();
    //TODO INTANCIE LAS LISTAS PORQUE NO PUEDO TESTEARLAS SINO, PORQUE SON NULLS
    @OneToMany(mappedBy = "game")
    @Fetch(FetchMode.SUBSELECT)
    private List<CardGameEntity> cards = new ArrayList<>();
    @OneToMany(mappedBy = "game", fetch = FetchType.EAGER)
    private List<PlayerEntity> players = new ArrayList<>();
}
