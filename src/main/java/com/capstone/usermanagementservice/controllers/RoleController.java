package com.capstone.usermanagementservice.controllers;

import com.capstone.usermanagementservice.dtos.RoleResponseDto;
import com.capstone.usermanagementservice.models.RoleModel;
import com.capstone.usermanagementservice.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @GetMapping("/user/{userId}/role")
    public ResponseEntity<List<RoleResponseDto>> getRoles(@PathVariable("userId") Long userId) {
        List<RoleModel> roleModels = roleService.getRoles(userId);
        return ResponseEntity.ok(userMapper.userToUserDetailResponseDto(user));
    }
}
