package com.capstone.usermanagementservice.util;

import com.capstone.usermanagementservice.clients.KafkaClient;
import com.capstone.usermanagementservice.dtos.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmailUtil {

    @Autowired
    private KafkaClient kafkaClient;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendSignupEmail(String email) throws JsonProcessingException {
        //using kafka
        String topic = "user_signup_email";
        EmailDto emailDto = new EmailDto();
        emailDto.setFrom("usermanagementservice@gmail.com");
        emailDto.setTo(email);
        emailDto.setSubject("Welcome to User management service");
        emailDto.setBody("Have a pleasant learning experience.");
        String message = objectMapper.writeValueAsString(emailDto);
        kafkaClient.sendMessage(topic, message);
    }

    public void sendPasswordResetEmail(String email, String token) throws JsonProcessingException {
        String topic = "password_reset_email";
        EmailDto emailDto = new EmailDto();
        emailDto.setFrom("usermanagementservice@gmail.com");
        emailDto.setTo(email);
        emailDto.setSubject("Welcome to User management service");
        emailDto.setBody("Your password reset request has been generated with \n token : " + token);
        String message = objectMapper.writeValueAsString(emailDto);
        kafkaClient.sendMessage(topic, message);
    }
}
