package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.constants.RoleEnum;
import com.capstone.usermanagementservice.constants.StateEnum;
import com.capstone.usermanagementservice.dtos.RoleUpdateRequestDto;
import com.capstone.usermanagementservice.models.RoleModel;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.repos.RoleRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Autowired
    private IUserService userService;

    @Override
    public List<RoleModel> getRoles(Long userId) {
        return roleRepo.findAllByUserIdAndState(userId, StateEnum.ACTIVE);
    }


    @Override
    public List<RoleModel> updateRoles(Long userId, RoleUpdateRequestDto roleUpdateRequestDto) {
        UserModel user = userService.getUserDetail(userId);
        Map<RoleEnum, RoleModel> roleEnumRoleModelMap = roleRepo.findAllByUser(user).stream()
                .collect(Collectors.toMap(RoleModel::getValue, roleModel -> roleModel));
        List<RoleModel> roleModels = new ArrayList<>();
        for (RoleEnum role : roleUpdateRequestDto.getRoles()) {
            RoleModel roleModel;
            if (roleEnumRoleModelMap.containsKey(role)) {
                roleModel = roleEnumRoleModelMap.remove(role);
            } else {
                roleModel = new RoleModel();
                roleModel.setUser(user);
                roleModel.setValue(role);
            }
            roleModel.setState(StateEnum.ACTIVE);
            roleRepo.save(roleModel);
            roleModels.add(roleModel);
        }
        for (RoleModel roleModel : roleEnumRoleModelMap.values()) {
            roleModel.setState(StateEnum.INACTIVE);
            roleRepo.save(roleModel);
        }
        return roleModels;
    }
}
