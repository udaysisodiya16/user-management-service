package com.capstone.usermanagementservice.util;

import com.capstone.usermanagementservice.clients.KafkaClient;
import com.capstone.usermanagementservice.dtos.EmailDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotificationUtil {

    @Autowired
    private KafkaClient kafkaClient;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendSignupNotification(String email) throws JsonProcessingException {
        EmailDto emailDto = new EmailDto();
        emailDto.setFrom("usermanagementservice@gmail.com");
        emailDto.setTo(email);
        emailDto.setSubject("Welcome to User management service");
        emailDto.setBody("Have a pleasant learning experience.");
        String message = objectMapper.writeValueAsString(emailDto);
        kafkaClient.sendSignupNotification(message);
    }

    public void sendPasswordResetNotification(String email, String token) throws JsonProcessingException {
        EmailDto emailDto = new EmailDto();
        emailDto.setFrom("usermanagementservice@gmail.com");
        emailDto.setTo(email);
        emailDto.setSubject("Welcome to User management service");
        emailDto.setBody("Your password reset request has been generated with \n token : " + token);
        String message = objectMapper.writeValueAsString(emailDto);
        kafkaClient.sendPasswordResetNotification(message);
    }
}
