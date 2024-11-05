package com.capstone.usermanagementservice.repos;

import com.capstone.usermanagementservice.models.SessionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionRepo extends JpaRepository<SessionModel, Long> {

    Optional<SessionModel> findByTokenAndUser_Id(String token, Long userId);

    Optional<SessionModel> findByTokenAndUser_Email(String jwt, String username);

}
