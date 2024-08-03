package com.example.notification_service.service.impl;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.example.notification_service.service.EmailService;
import org.springframework.stereotype.Service;

import java.security.Provider;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Override
    public void sendEmail(String to, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("order confirmation");
        message.setText(body);
        mailSender.send(message);    }
}
