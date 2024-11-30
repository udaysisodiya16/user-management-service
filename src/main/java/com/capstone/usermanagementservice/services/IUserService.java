package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.dtos.UserDetailUpdateRequestDto;
import com.capstone.usermanagementservice.models.UserModel;

public interface IUserService {

    UserModel getUserDetail(Long id);

    UserModel updateUserDetail(Long id, UserDetailUpdateRequestDto userDetailUpdateRequestDto);

    void checkValidUser(Long userId);
}
