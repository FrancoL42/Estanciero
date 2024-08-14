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
@Table(name="PROPERTIES")
@Entity

public class PropertyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String description;
    @Column
    private Integer rentValue;
    @Column
    private Integer buyValue;
    @Column
    private Integer farmBuyValue;
    @Column
    private Integer farmRentValue;
    @Column
    private Integer numberOfZonesInProvince;
    @ManyToOne
    @JoinColumn(name="property_Type_id")
    private PropertyTypeEntity propertyType;
    @ManyToOne
    @JoinColumn(name = "province_id")
    private ProvinceEntity province;
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private ZoneEntity zone;
}
