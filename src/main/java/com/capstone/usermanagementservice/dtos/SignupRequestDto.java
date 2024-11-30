package com.capstone.usermanagementservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Data
public class SignupRequestDto {

    @NotBlank(message = "email cannot blank")
    private String email;

    @NotBlank(message = "password cannot blank")
    private String password;

    @NotBlank(message = "firstName cannot blank")
    private String firstName;

    private String lastName;

    private String phoneNumber;
}
