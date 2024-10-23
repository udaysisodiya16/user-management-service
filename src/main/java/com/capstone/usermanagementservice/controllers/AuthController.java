package com.capstone.usermanagementservice.controllers;

import com.capstone.usermanagementservice.dtos.*;
import com.capstone.usermanagementservice.exceptions.InvalidCredentialsException;
import com.capstone.usermanagementservice.exceptions.UserAlreadyExistsException;
import com.capstone.usermanagementservice.mappers.UserMapper;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.services.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.validation.Valid;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private IAuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<UserDto> signup(@RequestBody @Valid SignupRequestDto signupRequestDto) {
        try {
            UserModel user = authService.signup(signupRequestDto.getEmail(), signupRequestDto.getPassword(),
                    signupRequestDto.getFirstName(), signupRequestDto.getLastName());
            UserDto userDto = userMapper.userToUserDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException existsException) {
            throw new RuntimeException(existsException.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            Pair<UserModel, MultiValueMap<String, String>> userWithHeaders = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            if (userWithHeaders.a == null) {
                return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
            }
            UserDto userDto = userMapper.userToUserDto(userWithHeaders.a);
            return new ResponseEntity<>(userDto, userWithHeaders.b, HttpStatus.OK);
        } catch (InvalidCredentialsException exception) {
            throw new RuntimeException(exception.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        //Learners need to implement after 6 sept
        return null;
    }

    @PostMapping("/validateToken")
    public Boolean validateToken(@RequestBody ValidateTokenDto validateTokenDto) {
        Boolean result = authService.validateToken(validateTokenDto.getToken(), validateTokenDto.getUserId());
        if (!result) {
            throw new RuntimeException("Please login again");
        }
        return result;
    }

}
