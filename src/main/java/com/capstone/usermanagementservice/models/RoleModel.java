package com.capstone.usermanagementservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "role")
public class RoleModel extends BaseModel {
    private String value;
}
