package com.example.notification_service.service.impl;

import com.example.notification_service.repository.NotificationRepository;
import com.example.notification_service.repository.entity.NotificationEntity;
import com.example.notification_service.service.EmailService;
import com.example.notification_service.service.NotificationRetryJob;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationRetryJobImpl implements NotificationRetryJob {

    private final NotificationRepository notificationRepository;

    private final EmailService emailService;

    @Override
    @Scheduled(fixedRate = 60000) // Runs every 60 seconds
    public void retryFailedNotifications() {
        List<NotificationEntity> failedNotifications = fetchUnSentNotifications();
        for (NotificationEntity notification : failedNotifications) {
            if (notification.getRetryCount() < EmailServiceImpl.MAX_RETRIES) {
                emailService.sendEmail(notification);
            } else {
                // Log or handle max retry reached scenario
                System.out.println("Max retry limit reached for notification id: " + notification.getId());
            }
        }
    }

    //@Cacheable
    private List<NotificationEntity> fetchUnSentNotifications() {
        List<NotificationEntity> failedNotifications = notificationRepository.findBySentFalse();
        return failedNotifications;
    }
}
