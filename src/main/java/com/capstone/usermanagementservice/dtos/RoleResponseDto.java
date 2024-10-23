package com.capstone.usermanagementservice.dtos;

import com.capstone.usermanagementservice.models.constants.RoleEnum;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RoleResponseDto {

    private RoleEnum role;

    private String display;

}
