package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.models.RoleModel;

import java.util.List;

public interface IRoleService {

    List<RoleModel> getRoles(Long userId);

}
