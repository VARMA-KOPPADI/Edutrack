package in.varma.edutrack.dto;

import in.varma.edutrack.entity.CityEntity;
import in.varma.edutrack.entity.CountryEntity;
import in.varma.edutrack.entity.StateEntity;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CounsellorDto {

    private Integer userId;

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
