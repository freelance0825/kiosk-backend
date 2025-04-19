package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.medicalpackagehistory.MedicalPackageHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.MedicalPackageHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class MedicalPackageHistoryMapperDecorator implements MedicalPackageHistoryMapper {

    @Autowired
    @Qualifier("delegate")
    private MedicalPackageHistoryMapper delegate;

    @Override
    public MedicalPackageHistoryResponseDto toDto(MedicalPackageHistoryEntity medicalPackageHistoryEntity) {
        if (medicalPackageHistoryEntity == null) {
            return null;
        }

        MedicalPackageHistoryResponseDto dto = delegate.toDto(medicalPackageHistoryEntity);
        dto.setUserId(medicalPackageHistoryEntity.getPatient().getId());
        dto.setUserName(medicalPackageHistoryEntity.getPatient().getName());
        dto.setPackageName(String.valueOf(medicalPackageHistoryEntity.getName()));
        dto.setUserAge(medicalPackageHistoryEntity.getPatient().getAge());
        dto.setUserGender(medicalPackageHistoryEntity.getPatient().getGender());
        dto.setUserAddress(medicalPackageHistoryEntity.getPatient().getAddress());
        dto.setUserDob(medicalPackageHistoryEntity.getPatient().getDateOfBirth());
        dto.setUserPhoneNumber(medicalPackageHistoryEntity.getPatient().getPhoneNumber());
        dto.setCreatedAt(medicalPackageHistoryEntity.getCreatedAt());

        return dto;
    }
}
