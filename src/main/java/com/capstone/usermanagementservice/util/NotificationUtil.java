package com.capstone.usermanagementservice.util;

import com.capstone.usermanagementservice.clients.KafkaClient;
import com.capstone.usermanagementservice.dtos.EmailDetailDto;
import com.capstone.usermanagementservice.dtos.NotificationType;
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
        userNotificationDto.setType(NotificationType.EMAIL);
        EmailDetailDto emailDetailDto = new EmailDetailDto();
        emailDetailDto.setFrom("usermanagementservice@gmail.com");
        emailDetailDto.setTo(email);
        emailDetailDto.setSubject("Welcome to User management service");
        emailDetailDto.setBody("Have a pleasant learning experience.");
        userNotificationDto.setEmailDetail(emailDetailDto);
        String message = objectMapper.writeValueAsString(userNotificationDto);
        kafkaClient.sendSignupNotification(message);
    }

    public void sendPasswordResetNotification(String email, String token) throws JsonProcessingException {
        UserNotificationDto userNotificationDto = new UserNotificationDto();
        userNotificationDto.setType(NotificationType.EMAIL);
        EmailDetailDto emailDetailDto = new EmailDetailDto();
        emailDetailDto.setFrom("usermanagementservice@gmail.com");
        emailDetailDto.setTo(email);
        emailDetailDto.setSubject("Welcome to User management service");
        emailDetailDto.setBody("Your password reset request has been generated with \n token : " + token);
        userNotificationDto.setEmailDetail(emailDetailDto);
        String message = objectMapper.writeValueAsString(userNotificationDto);
        kafkaClient.sendPasswordResetNotification(message);
    }
}
