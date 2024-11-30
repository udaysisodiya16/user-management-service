package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.dtos.AddressRequestDto;
import com.capstone.usermanagementservice.models.AddressModel;

import java.util.List;

public interface IAddressService {

    List<AddressModel> getAllUserAddresses(Long userId);

    AddressModel getUserAddress(Long addressId);

    AddressModel addUserAddress(Long userId, AddressRequestDto addressRequestDto);

    AddressModel updateUserAddress(Long addressId, AddressRequestDto addressRequestDto);

    Boolean deleteUserAddress(Long addressId);

}
