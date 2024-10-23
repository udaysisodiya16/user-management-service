package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.constants.RoleEnum;
import com.capstone.usermanagementservice.constants.StateEnum;
import com.capstone.usermanagementservice.dtos.RoleUpdateRequestDto;
import com.capstone.usermanagementservice.models.RoleModel;
import com.capstone.usermanagementservice.repos.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public List<RoleModel> getRoles(Long userId) {
        return roleRepo.findAllByUserIdAndState(userId, StateEnum.ACTIVE.name());
    }


    @Override
    public List<RoleModel> updateRoles(Long userId, RoleUpdateRequestDto roleUpdateRequestDto) {
        Map<RoleEnum, RoleModel> roleEnumRoleModelMap = roleRepo.findAllByUserId(userId).stream()
                .collect(Collectors.toMap(RoleModel::getValue, roleModel -> roleModel));
        for (RoleEnum role : roleUpdateRequestDto.getRoles()) {
            RoleModel roleModel;
            if (roleEnumRoleModelMap.containsKey(role)) {
                roleModel = roleEnumRoleModelMap.remove(role);
            } else {
                roleModel = new RoleModel();
                roleModel.setUserId(userId);
                roleModel.setValue(role);
            }
            roleModel.setState(StateEnum.ACTIVE);
            roleRepo.save(roleModel);
        }
        for (RoleModel roleModel : roleEnumRoleModelMap.values()) {
            roleModel.setState(StateEnum.INACTIVE);
            roleRepo.save(roleModel);
        }
        return roleRepo.findAllByUserIdAndState(userId, StateEnum.ACTIVE.name());
    }
}
