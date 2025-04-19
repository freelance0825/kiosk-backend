package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.testhistory.MedicalTestHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class MedicalTestMapperDecorator implements MedicalTestMapper {

    @Autowired
    @Qualifier("delegate")
    private MedicalTestMapper delegate;

    @Override
    public MedicalTestHistoryResponseDto toDto(TestHistoryEntity testHistoryEntity) {
        if (testHistoryEntity == null) {
            return null;
        }

        MedicalTestHistoryResponseDto dto = delegate.toDto(testHistoryEntity);

        UserEntity user = testHistoryEntity.getUsers();
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setUserAge(user.getAge());
        dto.setUserGender(user.getGender());
        dto.setUserAddress(user.getAddress());
        dto.setUserDob(user.getDateOfBirth());
        dto.setUserPhoneNumber(user.getPhoneNumber());

        // Custom logic for package name
        if (testHistoryEntity.getCustomPackage() != null) {
            dto.setPackageName(testHistoryEntity.getCustomPackage().getName().name());
        } else if (testHistoryEntity.getMedicalPackage() != null) {
            dto.setPackageName(String.valueOf(testHistoryEntity.getMedicalPackage().getName()));
        }

        dto.setTests(delegate.toTestDto(testHistoryEntity));
        dto.setCreatedAt(testHistoryEntity.getCreatedAt());

        return dto;
    }
}
