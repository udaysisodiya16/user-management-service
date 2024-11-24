package com.capstone.usermanagementservice.dtos;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailUpdateRequestDto {

    @NotBlank(message = "email cannot blank")
    private String email;

    @NotBlank(message = "firstName cannot blank")
    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

}
