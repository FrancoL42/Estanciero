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
@Table(name="SQUARES")
@Entity

public class SquareEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 //   @Column
 //   private String description;
    @Column
    private Integer number;
    @Column
    private Integer prizeAmount;
    @ManyToOne
    @JoinColumn(name = "property_id")
    private PropertyEntity property;
    @ManyToOne
    @JoinColumn(name = "squareType_id")
    private SquareTypeEntity squareType;
}
