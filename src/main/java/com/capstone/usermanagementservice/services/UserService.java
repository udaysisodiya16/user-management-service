package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.dtos.UserDetailUpdateRequestDto;
import com.capstone.usermanagementservice.exceptions.NotFoundException;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.repos.RoleRepo;
import com.capstone.usermanagementservice.repos.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserService implements IUserService {

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
        UserModel userModel = userRepo.findById(id).orElseThrow(() -> new NotFoundException("User Not Exist"));
        userModel.setEmail(userDetailUpdateRequestDto.getEmail());
        userModel.setFirstName(userDetailUpdateRequestDto.getFirstName());
        userModel.setLastName(userDetailUpdateRequestDto.getLastName());
        userModel.setPhoneNumber(userDetailUpdateRequestDto.getPhoneNumber());
        return userRepo.save(userModel);
    }

    @Override
    public void checkValidUser(Long userId) {
        userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User Not Exist"));
    }

}
