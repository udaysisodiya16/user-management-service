package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.models.RoleModel;
import com.capstone.usermanagementservice.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<RoleModel> getRoles(Long userId) {
        return roleRepo.findAllByUserId(userId);
    }

}
