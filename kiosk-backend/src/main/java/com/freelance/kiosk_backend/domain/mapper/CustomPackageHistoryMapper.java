package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.custompackagehistory.CustomPackageHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.CustomPackageHistoryEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(CustomPackageHistoryMapperDecorator.class)
public interface CustomPackageHistoryMapper {

    CustomPackageHistoryResponseDto toDto(CustomPackageHistoryEntity customPackageHistoryEntity);
    
}
