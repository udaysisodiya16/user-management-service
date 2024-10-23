package com.capstone.usermanagementservice.controllers;

import com.capstone.usermanagementservice.dtos.UserDetailResponseDto;
import com.capstone.usermanagementservice.dtos.UserDetailUpdateRequestDto;
import com.capstone.usermanagementservice.mappers.UserMapper;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.services.IUserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDetailResponseDto> getUserDetail(@PathVariable("id") Long id) {
        UserModel user = userService.getUserDetail(id);
        return ResponseEntity.ok(userMapper.userToUserDetailResponseDto(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDetailResponseDto> updateUserDetail(@PathVariable("id") Long id,
                                                    @RequestBody @Valid UserDetailUpdateRequestDto userDetailUpdateRequestDto) {
        UserModel user = userService.updateUserDetail(id, userDetailUpdateRequestDto);
        return ResponseEntity.ok(userMapper.userToUserDetailResponseDto(user));
    }

}
