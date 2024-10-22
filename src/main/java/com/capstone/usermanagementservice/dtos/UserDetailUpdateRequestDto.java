package com.capstone.usermanagementservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDetailUpdateRequestDto {

    @NotBlank(message = "email cannot blank")
    private String email;

    @NotBlank(message = "firstName cannot blank")
    private String firstName;

    private String lastName;

    private Set<RoleDto> roles;
}
