package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import com.freelance.kiosk_backend.domain.mapper.NotificationMapper;
import com.freelance.kiosk_backend.infrastructure.port.NotificationPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationPersistencePort notificationPersistencePort;

    private final NotificationMapper notificationMapper;

    public List<NotificationResponseDto> getNotificationsByUserId(Long patientId) {
        log.info("Getting notifications for user ID: {}", patientId);

        List<NotificationEntity> notificationList = notificationPersistencePort.findByUserId(patientId);
        return notificationMapper.toDtoList(notificationList);
    }
}
