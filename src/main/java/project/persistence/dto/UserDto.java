package project.persistence.dto;

import lombok.Data;
import project.persistence.model.HashFunctionType;

import javax.validation.constraints.NotNull;

@Data
public class UserDto {

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    private String password;

    @NotNull
    private String matchingPassword;

    private String email;

    private HashFunctionType hashFunctionType;
}
