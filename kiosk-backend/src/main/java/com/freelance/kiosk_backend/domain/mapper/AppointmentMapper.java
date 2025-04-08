package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentRequestDto;
import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    // RequestDto to Entity
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "name", ignore = true)
    @Mapping(target = "specialization", ignore = true)
    @Mapping(target = "imageBase64", ignore = true)
    AppointmentEntity toEntity(AppointmentRequestDto dto);

    AppointmentResponseDto toDto(AppointmentEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAppointmentFromDto(AppointmentRequestDto dto, @MappingTarget AppointmentEntity entity);

    List<AppointmentResponseDto> toDtoList(List<AppointmentEntity> appointmentEntities);

}
