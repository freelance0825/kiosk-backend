package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(NotificationMapperDecorator.class)
public interface NotificationMapper {

    NotificationResponseDto toDto(NotificationEntity notificationEntity);

}
