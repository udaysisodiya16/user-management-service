package com.capstone.usermanagementservice.clients;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaClient {

    @Value("${kafka.topic.user.signup.notification}")
    private String userSignupTopic;

    @Value("${kafka.topic.password.reset.notification}")
    private String passwordResetTopic;

    //data type of topic
    //data type of message
    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaClient(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendSignupNotification(String message) {
        kafkaTemplate.send(userSignupTopic, message);
    }

    public void sendPasswordResetNotification(String message) {
        kafkaTemplate.send(passwordResetTopic, message);
    }
}
