package com.capstone.usermanagementservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Setter
@Getter
@Entity(name = "user")
public class UserModel extends BaseModel {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;

    @ManyToMany
    private Set<RoleModel> roles;
}
