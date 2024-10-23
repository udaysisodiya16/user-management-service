package com.capstone.usermanagementservice.controllers;

import com.capstone.usermanagementservice.dtos.RoleResponseDto;
import com.capstone.usermanagementservice.dtos.RoleUpdateRequestDto;
import com.capstone.usermanagementservice.mappers.RoleMapper;
import com.capstone.usermanagementservice.models.RoleModel;
import com.capstone.usermanagementservice.services.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController {

    @Autowired
    private IRoleService roleService;

    @Autowired
    private RoleMapper roleMapper;

    @GetMapping("/user/{userId}/role")
    public ResponseEntity<List<RoleResponseDto>> getRoles(@PathVariable("userId") Long userId) {
        List<RoleModel> roleModels = roleService.getRoles(userId);
        return ResponseEntity.ok(roleMapper.roleModelsToRoleResponseDtos(roleModels));
    }

    @PutMapping("/user/{userId}/role")
    public ResponseEntity<List<RoleResponseDto>> updateRoles(@PathVariable("userId") Long userId,
                                                             @RequestBody RoleUpdateRequestDto roleUpdateRequestDto) {
        List<RoleModel> roleModels = roleService.updateRoles(userId, roleUpdateRequestDto);
        return ResponseEntity.ok(roleMapper.roleModelsToRoleResponseDtos(roleModels));
    }
}
