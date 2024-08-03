package com.example.notification_service.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class NotificationDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private String orderId;
    private String customerEmail;
    private String merchantEmail;
    private String message;
    // getters and setters
}
