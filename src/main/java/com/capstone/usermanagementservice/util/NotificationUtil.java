package com.capstone.usermanagementservice.util;

import com.capstone.usermanagementservice.clients.KafkaClient;
import com.capstone.usermanagementservice.dtos.NotificationDto;
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
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setFrom("usermanagementservice@gmail.com");
        notificationDto.setTo(email);
        notificationDto.setSubject("Welcome to User management service");
        notificationDto.setBody("Have a pleasant learning experience.");
        String message = objectMapper.writeValueAsString(notificationDto);
        kafkaClient.sendSignupNotification(message);
    }

    public void sendPasswordResetNotification(String email, String token) throws JsonProcessingException {
        NotificationDto notificationDto = new NotificationDto();
        notificationDto.setFrom("usermanagementservice@gmail.com");
        notificationDto.setTo(email);
        notificationDto.setSubject("Welcome to User management service");
        notificationDto.setBody("Your password reset request has been generated with \n token : " + token);
        String message = objectMapper.writeValueAsString(notificationDto);
        kafkaClient.sendPasswordResetNotification(message);
    }
}
