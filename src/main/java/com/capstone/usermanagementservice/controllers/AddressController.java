package com.capstone.usermanagementservice.controllers;

import com.capstone.usermanagementservice.dtos.AddressRequestDto;
import com.capstone.usermanagementservice.dtos.AddressResponseDto;
import com.capstone.usermanagementservice.mappers.AddressMapper;
import com.capstone.usermanagementservice.models.AddressModel;
import com.capstone.usermanagementservice.services.IAddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    @Autowired
    private IAddressService addressService;

    @Autowired
    private AddressMapper addressMapper;

    @GetMapping("/{userId}/")
    public ResponseEntity<List<AddressResponseDto>> getAllUserAddresses(@RequestParam("userId") Long userId) {
        List<AddressModel> addresses = addressService.getAllUserAddresses(userId);
        return ResponseEntity.ok(addressMapper.addressModelsToAddressResponseDtos(addresses));
    }

    @GetMapping("/{addressId}/")
    public ResponseEntity<AddressResponseDto> getUserAddress(@PathVariable("addressId") Long addressId) {
        AddressModel address = addressService.getUserAddress(addressId);
        return ResponseEntity.ok(addressMapper.addressModelToAddressResponseDto(address));
    }

    @PostMapping
    public ResponseEntity<AddressResponseDto> addUserAddress(@RequestParam("userId") Long userId,
                                                             @RequestBody @Valid AddressRequestDto addressRequestDto) {
        AddressModel address = addressService.addUserAddress(userId, addressRequestDto);
        return ResponseEntity.ok(addressMapper.addressModelToAddressResponseDto(address));
    }

    @PutMapping("/{addressId}/")
    public ResponseEntity<AddressResponseDto> updateUserAddress(@PathVariable("addressId") Long addressId,
                                                                @RequestBody @Valid AddressRequestDto addressRequestDto) {
        AddressModel address = addressService.updateUserAddress(addressId, addressRequestDto);
        return ResponseEntity.ok(addressMapper.addressModelToAddressResponseDto(address));
    }

    @DeleteMapping("/{addressId}/")
    public ResponseEntity<Boolean> deleteUserAddress(@PathVariable("addressId") Long addressId) {
        Boolean status = addressService.deleteUserAddress(addressId);
        return ResponseEntity.ok(status);
    }
}
