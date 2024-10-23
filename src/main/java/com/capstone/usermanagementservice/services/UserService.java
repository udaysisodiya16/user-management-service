package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.dtos.UserDetailUpdateRequestDto;
import com.capstone.usermanagementservice.exceptions.NotFoundException;
import com.capstone.usermanagementservice.models.RoleModel;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.repos.RoleRepo;
import com.capstone.usermanagementservice.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements IUserService{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public UserModel getUserDetail(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new NotFoundException("User Not Exist"));
    }

    @Override
    public UserModel updateUserDetail(Long id, UserDetailUpdateRequestDto userDetailUpdateRequestDto) {
        return null;
    }

    @Override
    public List<RoleModel> getUserRoles(Long id) {
        return List.of();
    }

    @Override
    public List<RoleModel> updateUserRoles(Long id) {
        return List.of();
    }
}
