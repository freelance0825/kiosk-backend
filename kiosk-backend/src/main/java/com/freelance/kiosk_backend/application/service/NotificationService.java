package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import com.freelance.kiosk_backend.domain.mapper.NotificationMapper;
import com.freelance.kiosk_backend.infrastructure.port.NotificationPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationPersistencePort notificationPersistencePort;

    private final NotificationMapper notificationMapper;

    public List<NotificationResponseDto> getNotificationsByUserId(Long patientId) {
        log.info("Getting notifications for user ID: {}", patientId);

        List<NotificationEntity> notificationList = notificationPersistencePort.findByUserId(patientId);
        return buildResponse(notificationList);
    }

    private List<NotificationResponseDto> buildResponse(List<NotificationEntity> notificationList) {
        return notificationList.stream()
                .sorted(Comparator.comparing(NotificationEntity::getCreatedAt).reversed())
                .map(entity -> {
                    NotificationResponseDto response = notificationMapper.toDto(entity);
                    setIds(response, entity);
                    return response;
                })
                .collect(Collectors.toList());
    }

    // Helper method to manually set the 3 IDs
    private void setIds(NotificationResponseDto dto, NotificationEntity entity) {
        if (entity.getAppointment() != null) {
            dto.setAppointmentId(entity.getAppointment().getId());
        }
        if (entity.getDoctor() != null) {
            dto.setApptDoctorId(entity.getDoctor().getId());
        }
        if (entity.getUsers() != null) {
            dto.setApptUserId(entity.getUsers().getId());
        }
    }
}
