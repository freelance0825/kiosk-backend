package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.application.dto.prescription.PrescriptionResponseDto;
import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrescriptionMapper {

    PrescriptionEntity toEntity(MedicineDto dto);

    PrescriptionResponseDto toDto(PrescriptionEntity entity);

}
