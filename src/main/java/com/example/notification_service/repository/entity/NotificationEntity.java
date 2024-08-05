package com.example.notification_service.repository.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "notification")
public class NotificationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "recipient_email")
    private String recipient;
    @Column(name = "message")
    private String message;
    @Column(name = "sent")
    private boolean sent;
    @Column(name = "retry_count")
    private int retryCount;

    // getters and setters
}
