package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.exceptions.UserAlreadyExistsException;
import com.capstone.usermanagementservice.models.UserModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.util.MultiValueMap;

public interface IAuthService {

    UserModel signup(String email, String password) throws UserAlreadyExistsException, JsonProcessingException;

    Pair<UserModel, MultiValueMap<String, String>> login(String email, String password) throws Exception;

    UserModel logout(String email);

    Boolean validateToken(String token, Long userId);

}
