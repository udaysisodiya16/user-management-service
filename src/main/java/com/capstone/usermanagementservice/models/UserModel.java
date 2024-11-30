package com.capstone.usermanagementservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity(name = "user")
public class UserModel extends BaseModel {
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;

    @OneToMany(mappedBy = "user")
    private Set<RoleModel> roles;

    @OneToMany(mappedBy = "user")
    @Where(clause = "state = 'ACTIVE'")
    private List<AddressModel> addresses;
}
