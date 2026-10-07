package in.varma.edutrack.dto;

import lombok.Data;

@Data
public class UserRegisterDto {

    private String name;

    private String email;

    private String tempPwd;

    private String pwdUpdated;

    private Long phno;

    private Integer countryId;

    private Integer stateId;

    private Integer cityId;
}
