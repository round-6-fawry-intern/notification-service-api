package com.example.notification_service.rest;

import com.example.notification_service.dto.NotificationDto;
import com.example.notification_service.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationResource {

    @Autowired
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    private final EmailService emailService;
    @PostMapping("/sendOrderNotification")
    public ResponseEntity<String> sendOrderNotification(@RequestBody NotificationDto request) {
        //emailService.sendEmail(request.getCustomerEmail(), request.getMessage());

         rabbitTemplate.convertAndSend("orderNotifications", request);
        return ResponseEntity.ok("Order notification sent!");
    }
}
