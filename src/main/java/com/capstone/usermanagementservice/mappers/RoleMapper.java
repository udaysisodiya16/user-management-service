package com.capstone.usermanagementservice.mappers;

import com.capstone.usermanagementservice.dtos.RoleResponseDto;
import com.capstone.usermanagementservice.models.RoleModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "role", source = "value")
    RoleResponseDto roleModelToRoleResponseDto(RoleModel roleModels);

    List<RoleResponseDto> roleModelsToRoleResponseDtos(List<RoleModel> roleModels);

}
