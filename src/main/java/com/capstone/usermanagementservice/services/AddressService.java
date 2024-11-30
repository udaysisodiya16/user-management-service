package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.dtos.AddressRequestDto;
import com.capstone.usermanagementservice.models.AddressModel;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService {
    @Override
    public List<AddressModel> getAllUserAddresses(Long userId) {
        return List.of();
    }

    @Override
    public AddressModel getUserAddress(Long addressId) {
        return null;
    }

    @Override
    public AddressModel addUserAddress(Long userId, AddressRequestDto addressRequestDto) {
        return null;
    }

    @Override
    public AddressModel updateUserAddress(Long addressId, AddressRequestDto addressRequestDto) {
        return null;
    }

    @Override
    public Boolean deleteUserAddress(Long addressId) {
        return null;
    }
}
