package com.example.notification_service.service.impl;

import com.example.notification_service.dto.NotificationDto;
import com.example.notification_service.repository.entity.NotificationEntity;
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
        NotificationEntity customerNotification = new NotificationEntity();
        //NotificationEntity merchantNotification = new NotificationEntity();
        //merchantNotification.setRecipient(notificationDto.getMerchantEmail());
        notificationDtoToEntityMapper(notificationDto, customerNotification);
        emailService.sendEmail(customerNotification);
        //emailService.sendNewEmail(notificationDto.getMerchantEmail(), notificationDto.getMessage());
    }

    private void notificationDtoToEntityMapper(NotificationDto notificationDto, NotificationEntity notification) {
        notification.setRecipient(notificationDto.getCustomerEmail());
        notification.setMessage(notificationDto.getMessage());
        notification.setSent(false);
        notification.setRetryCount(0);
    }

}
