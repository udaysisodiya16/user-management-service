package com.capstone.usermanagementservice.controllers;

import com.capstone.usermanagementservice.dtos.*;
import com.capstone.usermanagementservice.exceptions.UserAlreadyExistsException;
import com.capstone.usermanagementservice.mappers.UserMapper;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.services.IAuthService;
import com.fasterxml.jackson.core.JsonProcessingException;
import jakarta.servlet.http.HttpServletRequest;
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
            UserModel user = authService.signup(signupRequestDto.getEmail(), signupRequestDto.getPassword());
            UserDto userDto = userMapper.userToUserDto(user);
            return new ResponseEntity<>(userDto, HttpStatus.CREATED);
        } catch (UserAlreadyExistsException existsException) {
            throw new RuntimeException(existsException.getMessage());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginRequestDto loginRequestDto) throws Exception {
        Pair<UserModel, MultiValueMap<String, String>> userWithHeaders = authService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        UserDto userDto = userMapper.userToUserDto(userWithHeaders.a);
        return new ResponseEntity<>(userDto, userWithHeaders.b, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<UserDto> logout(@RequestBody LogoutRequestDto logoutRequestDto) {
        UserModel user = authService.logout(logoutRequestDto.getEmail());
        UserDto userDto = userMapper.userToUserDto(user);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @PostMapping("/validateToken")
    public Boolean validateToken(@RequestBody ValidateTokenDto validateTokenDto) {
        Boolean result = authService.validateToken(validateTokenDto.getToken(), validateTokenDto.getUserId());
        if (!result) {
            throw new RuntimeException("Please login again");
        }
        return result;
    }

    @PostMapping("/resetPasswordRequest")
    public ResponseEntity<Boolean> requestPasswordReset(@RequestBody @Valid PasswordResetRequestDto requestDto) throws JsonProcessingException {
        Boolean status = authService.requestPasswordReset(requestDto.getEmail());
        return ResponseEntity.ok(status);
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<Boolean> resetPassword(HttpServletRequest httpRequest, @RequestBody @Valid PasswordResetDto passwordResetDto) {
        Boolean status = authService.resetPassword(httpRequest, passwordResetDto.getNewPassword());
        return ResponseEntity.ok(status);
    }

}
