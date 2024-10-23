package com.capstone.usermanagementservice.mappers;

import com.capstone.usermanagementservice.dtos.UserDetailResponseDto;
import com.capstone.usermanagementservice.dtos.UserDto;
import com.capstone.usermanagementservice.models.UserModel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(UserModel user);

    UserDetailResponseDto userToUserDetailResponseDto(UserModel user);
}
