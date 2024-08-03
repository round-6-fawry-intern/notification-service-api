package com.example.notification_service.service.impl;

import com.example.notification_service.dto.NotificationDto;
import com.example.notification_service.service.EmailService;
import com.example.notification_service.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final EmailService emailService;

    @Override
    @RabbitListener(queues = "orderNotifications")
    public void receiveMessage(NotificationDto notificationDto) {
        // Process and send notifications
        emailService.sendEmail(notificationDto.getCustomerEmail(), notificationDto.getMessage());
        //emailService.sendEmail(notificationDto.getMerchantEmail(), notificationDto.getMessage());
    }
}
