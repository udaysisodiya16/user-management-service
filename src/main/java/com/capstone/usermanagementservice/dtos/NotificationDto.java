package com.capstone.usermanagementservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NotificationDto {
    private String to;
    private String from;
    private String subject;
    private String body;
}
