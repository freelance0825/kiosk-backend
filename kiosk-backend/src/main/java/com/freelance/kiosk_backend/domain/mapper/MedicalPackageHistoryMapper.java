package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.medicalpackagehistory.MedicalPackageHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.MedicalPackageHistoryEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
@DecoratedWith(MedicalPackageHistoryMapperDecorator.class)
public interface MedicalPackageHistoryMapper {

    MedicalPackageHistoryResponseDto toDto(MedicalPackageHistoryEntity entity);

    List<MedicalPackageHistoryResponseDto> toDtoList(List<MedicalPackageHistoryEntity> mpEntities);
    
}
