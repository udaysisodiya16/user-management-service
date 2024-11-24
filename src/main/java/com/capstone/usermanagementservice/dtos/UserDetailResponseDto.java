package com.capstone.usermanagementservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDetailResponseDto {

    private String email;

    private String firstName;

    private String lastName;

    private String address;

    private String phoneNumber;

}
