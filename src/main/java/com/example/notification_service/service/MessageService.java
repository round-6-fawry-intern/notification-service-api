package com.example.notification_service.service;

import com.example.notification_service.dto.NotificationDto;

public interface MessageService {
    public void receiveMessage(NotificationDto notificationDto);
}
