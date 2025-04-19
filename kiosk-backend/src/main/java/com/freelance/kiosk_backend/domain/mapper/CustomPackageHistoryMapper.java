package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.custompackagehistory.CustomPackageHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.CustomPackageHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomPackageHistoryMapper {

    @Mapping(source = "patient.id", target = "patientId")
    CustomPackageHistoryResponseDto toDto(CustomPackageHistoryEntity customPackageHistoryEntity);
    
}
