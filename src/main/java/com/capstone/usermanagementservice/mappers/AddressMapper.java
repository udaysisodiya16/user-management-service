package com.capstone.usermanagementservice.mappers;

import com.capstone.usermanagementservice.dtos.AddressResponseDto;
import com.capstone.usermanagementservice.models.AddressModel;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressResponseDto addressModelToAddressResponseDto(AddressModel address);

    List<AddressResponseDto> addressModelsToAddressResponseDtos(List<AddressModel> addresses);
}
