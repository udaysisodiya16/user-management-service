package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.models.AddressModel;

import java.util.List;

public interface IAddressService {

    List<AddressModel> getAllUserAddresses(Long userId);

    AddressModel getUserAddress(Long addressId);

    AddressModel addUserAddress(Long userId, AddressModel address);

    AddressModel updateUserAddress(Long addressId, AddressModel address);

    Boolean deleteUserAddress(Long addressId);

}
