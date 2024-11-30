package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.constants.StateEnum;
import com.capstone.usermanagementservice.exceptions.NotFoundException;
import com.capstone.usermanagementservice.models.AddressModel;
import com.capstone.usermanagementservice.repos.AddressRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AddressService implements IAddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Override
    public List<AddressModel> getAllUserAddresses(Long userId) {
        return addressRepo.findByUserId(userId);
    }

    @Override
    public AddressModel getUserAddress(Long addressId) {
        return addressRepo.findById(addressId).orElseThrow(() -> new NotFoundException("Address not found"));
    }

    @Override
    public AddressModel addUserAddress(Long userId, AddressModel address) {
        address.setUserId(userId);
        addressRepo.save(address);
        return address;
    }

    @Override
    public AddressModel updateUserAddress(Long addressId, AddressModel address) {
        AddressModel oldAddress = addressRepo.findById(addressId).orElseThrow(() -> new NotFoundException("Address not found"));
        address.setId(oldAddress.getId());
        addressRepo.save(address);
        return address;
    }

    @Override
    public Boolean deleteUserAddress(Long addressId) {
        AddressModel address = addressRepo.findById(addressId).orElseThrow(() -> new NotFoundException("Address not found"));
        address.setState(StateEnum.INACTIVE);
        addressRepo.save(address);
        return true;
    }
}
