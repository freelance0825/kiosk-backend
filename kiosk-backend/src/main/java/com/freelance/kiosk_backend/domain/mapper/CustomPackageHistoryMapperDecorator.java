package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.custompackagehistory.CustomPackageHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.CustomPackageHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class CustomPackageHistoryMapperDecorator implements CustomPackageHistoryMapper {

    @Autowired
    @Qualifier("delegate")
    private CustomPackageHistoryMapper delegate;

    @Override
    public CustomPackageHistoryResponseDto toDto(CustomPackageHistoryEntity customPackageHistoryEntity) {
        if (customPackageHistoryEntity == null) {
            return null;
        }

        CustomPackageHistoryResponseDto dto = delegate.toDto(customPackageHistoryEntity);
        dto.setUserId(customPackageHistoryEntity.getPatient().getId());
        dto.setUserName(customPackageHistoryEntity.getPatient().getName());
        dto.setPackageName(String.valueOf(customPackageHistoryEntity.getName()));
        dto.setUserAge(customPackageHistoryEntity.getPatient().getAge());
        dto.setUserGender(customPackageHistoryEntity.getPatient().getGender());
        dto.setUserAddress(customPackageHistoryEntity.getPatient().getAddress());
        dto.setUserDob(customPackageHistoryEntity.getPatient().getDateOfBirth());
        dto.setUserPhoneNumber(customPackageHistoryEntity.getPatient().getPhoneNumber());
        dto.setCreatedAt(customPackageHistoryEntity.getCreatedAt());

        return dto;
    }

}
