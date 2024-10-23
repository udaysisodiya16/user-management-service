package com.capstone.usermanagementservice.dtos;

import com.capstone.usermanagementservice.constants.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RoleUpdateRequestDto {

    private List<RoleEnum> roles;

}
