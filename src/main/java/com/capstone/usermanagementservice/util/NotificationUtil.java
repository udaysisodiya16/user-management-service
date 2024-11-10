package com.capstone.usermanagementservice.util;

import com.capstone.usermanagementservice.clients.KafkaClient;
import com.capstone.usermanagementservice.dtos.UserNotificationDto;
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
        UserNotificationDto userNotificationDto = new UserNotificationDto();
        userNotificationDto.setFrom("usermanagementservice@gmail.com");
        userNotificationDto.setTo(email);
        userNotificationDto.setSubject("Welcome to User management service");
        userNotificationDto.setBody("Have a pleasant learning experience.");
        String message = objectMapper.writeValueAsString(userNotificationDto);
        kafkaClient.sendSignupNotification(message);
    }

    public void sendPasswordResetNotification(String email, String token) throws JsonProcessingException {
        UserNotificationDto userNotificationDto = new UserNotificationDto();
        userNotificationDto.setFrom("usermanagementservice@gmail.com");
        userNotificationDto.setTo(email);
        userNotificationDto.setSubject("Welcome to User management service");
        userNotificationDto.setBody("Your password reset request has been generated with \n token : " + token);
        String message = objectMapper.writeValueAsString(userNotificationDto);
        kafkaClient.sendPasswordResetNotification(message);
    }
}
