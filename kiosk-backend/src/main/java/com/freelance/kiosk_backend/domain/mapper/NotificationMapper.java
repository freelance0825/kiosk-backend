package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(target = "appointmentId", ignore = true)
    @Mapping(target = "apptDoctorId", ignore = true)
    @Mapping(target = "apptUserId", ignore = true)
    NotificationResponseDto toDto(NotificationEntity notificationEntities);

}
