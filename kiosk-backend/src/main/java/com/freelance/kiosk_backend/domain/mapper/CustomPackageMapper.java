package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CustomPackageMapper {

    CustomPackageResponseDto toDto(CustomPackageEntity entity);

    List<CustomPackageResponseDto> toDtoList(List<CustomPackageEntity> customPackageEntities);

}
