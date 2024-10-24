package com.capstone.usermanagementservice.services;

import com.capstone.usermanagementservice.clients.KafkaClient;
import com.capstone.usermanagementservice.dtos.EmailDto;
import com.capstone.usermanagementservice.exceptions.InvalidCredentialsException;
import com.capstone.usermanagementservice.exceptions.UserAlreadyExistsException;
import com.capstone.usermanagementservice.models.SessionModel;
import com.capstone.usermanagementservice.constants.StateEnum;
import com.capstone.usermanagementservice.models.UserModel;
import com.capstone.usermanagementservice.repos.SessionRepo;
import com.capstone.usermanagementservice.repos.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import javax.crypto.SecretKey;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService implements IAuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        user.setPassword(bCryptPasswordEncoder.encode(password));
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
    public Pair<UserModel,MultiValueMap<String,String>> login(String email, String password) throws InvalidCredentialsException {
        Optional<UserModel> userOptional = userRepo.findUserByEmail(email);

        if(userOptional.isPresent()) {
            UserModel user = userOptional.get();
            if(!bCryptPasswordEncoder.matches(password,user.getPassword())) {
              throw new InvalidCredentialsException("please provide correct password");
            }

            Map<String,Object> claims =new HashMap<>();
            claims.put("user_id__",user.getId());
            claims.put("roles",user.getRoles());
            long timeInMillis = System.currentTimeMillis();
            claims.put("iat",timeInMillis);
            claims.put("exp",timeInMillis+86400000);
            String token = Jwts.builder().claims(claims).signWith(secretKey).compact();

            MultiValueMap<String,String> headers = new LinkedMultiValueMap<>();
            headers.add(HttpHeaders.SET_COOKIE,token);

            Pair<UserModel,MultiValueMap<String,String>> p = new Pair<>(user,headers);

            SessionModel session = new SessionModel();
            session.setToken(token);
            session.setUser(user);
            session.setState(StateEnum.ACTIVE);
            sessionRepo.save(session);
            return p;
        }
        return null;
    }


    public Boolean validateToken(String token,Long userId) {
/*         Optional<Session> optionalSession = sessionRepo.findByTokenAndUser_Id(token,userId);

         if(!optionalSession.isPresent()) {
             return false;
         }

        JwtParser jwtParser = Jwts.parser().verifyWith(secretKey).build();
        Claims claims = jwtParser.parseSignedClaims(token).getPayload();

        Long expiry = (Long)claims.get("exp");
        Long currentTimeInMillis = System.currentTimeMillis();

        System.out.println(expiry);
        System.out.println(currentTimeInMillis);

        if(currentTimeInMillis > expiry) {
            System.out.println("TOKEN EXPIRED");
            //1. clear expired token from DB async with help of kAFKA
            //2. YOU can also trigger login API
            //3. Use same api in order service to validate user before getting order info.
            return false;
        }*/

        //List<Role> roles = (Long)claims.get("roles");
        //if(roles.contains())


        return true;
    }



    @Override
    public UserModel logout(String email) {
        return null;
    }
}
