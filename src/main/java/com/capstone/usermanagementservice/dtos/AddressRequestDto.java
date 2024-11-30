package com.capstone.usermanagementservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressRequestDto {
    private String number;
    private String street;
    private String city;
    private String pinCode;
    private String landmark;
    private Boolean isDefault;
}
