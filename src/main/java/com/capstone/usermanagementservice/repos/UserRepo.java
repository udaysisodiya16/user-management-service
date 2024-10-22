package com.capstone.usermanagementservice.repos;

import com.capstone.usermanagementservice.models.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<UserModel, Long> {

    Optional<UserModel> findUserByEmail(String email);

}
