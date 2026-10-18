package in.varma.edutrack.dto;

import in.varma.edutrack.entity.CityEntity;
import in.varma.edutrack.entity.CountryEntity;
import in.varma.edutrack.entity.StateEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
public class CounsellorDto {

    private Integer counsellorId;

    private String name;

    private String email;

    private String Pwd;

    private String pwdUpdated;

    private Long phno;

    private CountryEntity country;

    private StateEntity state;

    private CityEntity city;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
