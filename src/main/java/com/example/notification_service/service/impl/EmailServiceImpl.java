package com.example.notification_service.service.impl;
import com.example.notification_service.repository.NotificationRepository;
import com.example.notification_service.repository.entity.NotificationEntity;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import com.example.notification_service.service.EmailService;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.Properties;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;
    private final NotificationRepository notificationRepository;

    static final int MAX_RETRIES = 5;

    @Override
    public void sendEmail(NotificationEntity notification) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            setMessageProperties(notification, message);
            mailSender.send(message);
            notification.setSent(true);

        } catch (Exception e) {
            notification.setRetryCount(notification.getRetryCount() + 1);
        }
        finally {
            notificationRepository.save(notification);
        }
    }

    private void setMessageProperties(NotificationEntity notification, SimpleMailMessage message) {
        message.setTo(notification.getRecipient());
        message.setSubject("Order Confirmation");
        message.setText(notification.getMessage());
    }

        /*NotificationEntity notification = new NotificationEntity();
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Order confirmation");
            message.setText(body);
            mailSender.send(message);
            notification.setSent(true);


        } catch (Exception e) {
            notification.setSent(false);
            //TODO handle exception
            e.printStackTrace();
        }finally {
            insertNewMail(to, body, notification);
        }
*/

        /*SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject("order confirmation");
        message.setText(body);
        mailSender.send(message);
*/


    }

//    private void insertNewMail(String to, String body, NotificationEntity notification) {
//        notification.setRecipient(to);
//        notification.setMessage(body);
//        notification.setRetryCount(MAX_RETRIES);
//        notificationRepository.save(notification);
//    }

