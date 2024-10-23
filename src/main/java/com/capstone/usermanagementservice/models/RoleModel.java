package com.capstone.usermanagementservice.models;

import com.capstone.usermanagementservice.constants.RoleEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "role")
public class RoleModel extends BaseModel {

    @Enumerated(EnumType.STRING)
    private RoleEnum value;

    private Long userId;

}
