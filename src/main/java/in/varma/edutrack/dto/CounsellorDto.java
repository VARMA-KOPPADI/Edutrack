package in.varma.edutrack.dto;


import lombok.Data;

import java.time.LocalDate;

@Data
public class CounsellorDto {

    private Integer counsellorId;

    private String name;

    private String email;

    private String Pwd;

    private String pwdUpdated;

    private Long phno;

    private Integer country;

    private Integer state;

    private Integer city;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
