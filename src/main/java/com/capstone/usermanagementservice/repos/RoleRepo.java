package com.capstone.usermanagementservice.repos;

import com.capstone.usermanagementservice.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Long> {

    List<RoleModel> findAllByUserIdAndState(Long userId, String state);

    List<RoleModel> findAllByUserId(Long userId);
}
