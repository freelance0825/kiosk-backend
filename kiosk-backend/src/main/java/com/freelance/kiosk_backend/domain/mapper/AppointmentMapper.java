package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentRequestDto;
import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import com.freelance.kiosk_backend.application.dto.appointment.UpdateAppointmentRequestDto;
import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;
import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentMapper {

    AppointmentEntity toEntity(AppointmentRequestDto dto);

    AppointmentResponseDto toDto(AppointmentEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAppointmentFromDto(UpdateAppointmentRequestDto dto, @MappingTarget AppointmentEntity entity);

    @Mapping(target = "postConsultation", source = "postConsultation")
    List<AppointmentResponseDto> toDtoList(List<AppointmentEntity> appointmentEntities);

    MedicineDto toMedicineDto(PrescriptionEntity prescriptionEntity);

}
