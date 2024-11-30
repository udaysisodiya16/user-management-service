package com.capstone.usermanagementservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "address")
public class AddressModel extends BaseModel {
    private String number;
    private String street;
    private String city;
    private String pinCode;
    private String landmark;
    private Boolean isDefault;

    @ManyToOne
    private UserModel user;
}
