package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomPackageMapper {

    CustomPackageResponseDto toDto(CustomPackageEntity customPackageEntity);
    
}
