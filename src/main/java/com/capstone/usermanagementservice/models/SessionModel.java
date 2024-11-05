package com.capstone.usermanagementservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "session")
public class SessionModel extends BaseModel {

    private String token;

    @OneToOne
    private UserModel user;
}
