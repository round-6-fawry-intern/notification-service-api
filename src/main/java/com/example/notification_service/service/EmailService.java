package com.example.notification_service.service;

import com.example.notification_service.repository.entity.NotificationEntity;

public interface EmailService {
    public void sendEmail(NotificationEntity email);
}
