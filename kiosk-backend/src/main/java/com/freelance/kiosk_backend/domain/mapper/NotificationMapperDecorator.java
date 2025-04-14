package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class NotificationMapperDecorator implements NotificationMapper {

    @Autowired
    @Qualifier("delegate")
    private NotificationMapper delegate;

    @Override
    public NotificationResponseDto toDto(NotificationEntity notificationEntity) {
        if (notificationEntity == null) {
            return null;
        }

        NotificationResponseDto dto = delegate.toDto(notificationEntity);
        dto.setAppointmentId(notificationEntity.getAppointment() != null ? notificationEntity.getAppointment().getId() : null);
        dto.setApptDoctorId(notificationEntity.getDoctor() != null ? notificationEntity.getDoctor().getId() : null);
        dto.setApptUserId(notificationEntity.getUsers() != null ? notificationEntity.getUsers().getId() : null);

        return dto;
    }
}
