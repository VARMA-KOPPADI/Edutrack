package in.varma.edutrack.dto;

import lombok.Data;

@Data
public class ResetPasswordDto {

    private String email;

    private String oldPassword;

    private String newPassword;

    private String confirmPassword;

}
