package com.capstone.usermanagementservice.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class UserDetailResponseDto {
    private String email;
    private String password;
    private String firstName;
    private String lastName;

    private Set<RoleDto> roles;

}
