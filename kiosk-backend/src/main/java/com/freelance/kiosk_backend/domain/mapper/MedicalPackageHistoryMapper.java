package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.medicalpackagehistory.MedicalPackageHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.MedicalPackageHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalPackageHistoryMapper {

    @Mapping(source = "patient.id", target = "patientId")
    MedicalPackageHistoryResponseDto toDto(MedicalPackageHistoryEntity entity);

    List<MedicalPackageHistoryResponseDto> toDtoList(List<MedicalPackageHistoryEntity> mpEntities);
    
}
