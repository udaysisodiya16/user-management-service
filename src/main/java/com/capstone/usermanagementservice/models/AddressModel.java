package com.capstone.usermanagementservice.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserModel user;
}
