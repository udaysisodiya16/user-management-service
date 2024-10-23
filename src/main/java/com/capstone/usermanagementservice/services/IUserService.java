package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.dtos.UserDetailUpdateRequestDto;
import com.capstone.usermanagementservice.models.RoleModel;
import com.capstone.usermanagementservice.models.UserModel;

import java.util.List;

public interface IUserService {

    UserModel getUserDetail(Long id);

    UserModel updateUserDetail(Long id, UserDetailUpdateRequestDto userDetailUpdateRequestDto);

    List<RoleModel> getUserRoles(Long id);

    List<RoleModel> updateUserRoles(Long id);

}
