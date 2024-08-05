package com.example.notification_service.repository;

import com.example.notification_service.repository.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {

    List<NotificationEntity> findBySentFalse();


}
