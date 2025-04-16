package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.test.MedicalTestResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class MedicalTestMapperDecorator implements MedicalTestMapper {

    @Autowired
    @Qualifier("delegate")
    private MedicalTestMapper delegate;

    @Override
    public MedicalTestResponseDto toDto(TestEntity testEntity) {
        if (testEntity == null) {
            return null;
        }

        MedicalTestResponseDto dto = delegate.toDto(testEntity);

        UserEntity user = testEntity.getUsers();
        dto.setUserId(user.getId());
        dto.setUserName(user.getName());
        dto.setUserAge(user.getAge());
        dto.setUserGender(user.getGender());
        dto.setUserAddress(user.getAddress());
        dto.setUserDob(user.getDateOfBirth());
        dto.setUserPhoneNumber(user.getPhoneNumber());

        // Custom logic for package name
        if (testEntity.getCustomPackage() != null) {
            dto.setPackageName(testEntity.getCustomPackage().getName().name());
        } else if (testEntity.getMedicalPackage() != null) {
            dto.setPackageName(String.valueOf(testEntity.getMedicalPackage().getName()));
        }

        dto.setTests(delegate.toTestDto(testEntity));
        dto.setCreatedAt(testEntity.getCreatedAt());

        return dto;
    }
}
