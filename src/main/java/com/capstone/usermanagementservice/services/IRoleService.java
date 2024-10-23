package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.dtos.RoleUpdateRequestDto;
import com.capstone.usermanagementservice.models.RoleModel;

import java.util.List;

public interface IRoleService {

    List<RoleModel> getRoles(Long userId);

    List<RoleModel> updateRoles(Long userId, RoleUpdateRequestDto roleUpdateRequestDto);

}
