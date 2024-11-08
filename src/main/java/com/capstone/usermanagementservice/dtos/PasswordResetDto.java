package com.capstone.usermanagementservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordResetDto {

    @NotBlank
    private String email;

    @NotBlank
    private String newPassword;

}
