package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.clients.KafkaClient;
import com.capstone.usermanagementservice.constants.StateEnum;
import com.capstone.usermanagementservice.dtos.EmailDto;
import com.capstone.usermanagementservice.exceptions.InvalidCredentialsException;
import com.capstone.usermanagementservice.exceptions.NotFoundException;
import com.capstone.usermanagementservice.exceptions.UserAlreadyExistsException;
import com.capstone.usermanagementservice.models.SessionModel;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.repos.SessionRepo;
import com.capstone.usermanagementservice.repos.UserRepo;
import com.capstone.usermanagementservice.security.CustomUserDetails;
import com.capstone.usermanagementservice.util.JwtUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private SessionRepo sessionRepo;

    @Autowired
    private SecretKey secretKey;

    @Autowired
    private KafkaClient kafkaClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public UserModel signup(String email, String password) throws UserAlreadyExistsException, JsonProcessingException {
        Optional<UserModel> userOptional = userRepo.findUserByEmail(email);
        if (userOptional.isPresent()) {
            throw new UserAlreadyExistsException("Email already registered !!");
        }

        UserModel user = new UserModel();
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        user.setState(StateEnum.ACTIVE);
        userRepo.save(user);

        //using kafka

        String topic = "user_signedin";
        EmailDto emailDto = new EmailDto();
        emailDto.setFrom("anuragbatch@gmail.com");
        emailDto.setTo(email);
        emailDto.setSubject("Welcome to Scaler");
        emailDto.setBody("Have a pleasant learning experience.");
        String message = objectMapper.writeValueAsString(emailDto);
//            kafkaClient.sendMessage(topic,message);

        return user;
    }

    @Override
    public Pair<UserModel, MultiValueMap<String, String>> login(String email, String password) throws InvalidCredentialsException {
        UserModel user = userRepo.findUserByEmail(email).orElseThrow(() -> new NotFoundException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("please provide correct password");
        }

        String token = JwtUtil.generateToken(secretKey, new CustomUserDetails(user));

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add(HttpHeaders.SET_COOKIE, token);

        Pair<UserModel, MultiValueMap<String, String>> p = new Pair<>(user, headers);

        SessionModel session = new SessionModel();
        session.setToken(token);
        session.setUser(user);
        session.setState(StateEnum.ACTIVE);
        sessionRepo.save(session);
        return p;
    }

    @Override
    public UserModel logout(String email) {
        return null;
    }

    @Override
    public Boolean validateToken(String token, Long userId) {
        UserModel user = userRepo.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
        Optional<SessionModel> optionalSession = sessionRepo.findByTokenAndUser_Id(token, userId);
        if (optionalSession.isEmpty()) {
            return false;
        }
        return JwtUtil.validateToken(token, secretKey, new CustomUserDetails(user));
    }
}
