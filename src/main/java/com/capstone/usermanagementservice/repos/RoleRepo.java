package com.capstone.usermanagementservice.repos;

import com.capstone.usermanagementservice.models.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepo extends JpaRepository<RoleModel, Long> {
}
