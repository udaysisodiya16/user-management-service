package com.capstone.usermanagementservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserNotificationDto {

    private NotificationType type;

    private EmailDetailDto emailDetail;
}
