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
@Table(name="ZONES")
@Entity

public class ZoneEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String zone;

    public ZoneEntity(String Zone){
        this.zone = zone;
    }
}
