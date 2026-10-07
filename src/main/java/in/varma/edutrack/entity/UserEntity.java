package in.varma.edutrack.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Entity
@Table(name="user_master")
@Setter
@Getter
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String name;

    private String email;

    private String tempPwd;

    private String pwdUpdated;

    private Long phno;

    @ManyToOne
    @JoinColumn(name="country_id")
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name="state_id")
    private StateEntity state;

    @ManyToOne
    @JoinColumn(name="city_id")
    private CityEntity city;

    @CreationTimestamp
    private LocalDate createdAt;

    @UpdateTimestamp
    private LocalDate updatedAt;
}