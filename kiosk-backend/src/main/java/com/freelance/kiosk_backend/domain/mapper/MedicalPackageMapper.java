package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageResponseDto;
import com.freelance.kiosk_backend.domain.entity.MedicalPackageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalPackageMapper {

    @Mapping(source = "patient.id", target = "patientId")
    MedicalPackageResponseDto toDto(MedicalPackageEntity entity);

    List<MedicalPackageResponseDto> toDtoList(List<MedicalPackageEntity> mpEntities);
    
}
