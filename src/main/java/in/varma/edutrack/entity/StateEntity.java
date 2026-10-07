package in.varma.edutrack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "states_master")
@Setter
@Getter
public class StateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer stateId;

    private String stateName;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;
}