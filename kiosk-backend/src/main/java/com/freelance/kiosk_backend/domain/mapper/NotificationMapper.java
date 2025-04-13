package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    //@Mapping(target = "appointment", source = "appointment")
    List<NotificationResponseDto> toDtoList(List<NotificationEntity> notificationEntities);

}
