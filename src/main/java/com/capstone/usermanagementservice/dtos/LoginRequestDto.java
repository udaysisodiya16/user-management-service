package com.capstone.usermanagementservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto {

    @NotBlank
    private String email;

    @NotBlank
    private String password;
}
