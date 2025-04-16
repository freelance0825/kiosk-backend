package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomPackageMapper {

    @Mapping(source = "patient.id", target = "patientId")
    CustomPackageResponseDto toDto(CustomPackageEntity customPackageEntity);
    
}
