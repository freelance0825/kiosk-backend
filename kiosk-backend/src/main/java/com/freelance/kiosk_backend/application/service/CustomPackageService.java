package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.application.dto.test.TestResponseDto;
import com.freelance.kiosk_backend.application.dto.test.enums.TestCustomName;
import com.freelance.kiosk_backend.application.dto.test.enums.TestRange;
import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;
import com.freelance.kiosk_backend.domain.entity.TestEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.CustomPackageMapper;
import com.freelance.kiosk_backend.domain.mapper.TestMapper;
import com.freelance.kiosk_backend.infrastructure.port.CustomPackagePersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.TestPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.UserPersistencePort;
import com.freelance.kiosk_backend.shared.exception.InvalidRequestException;
import com.freelance.kiosk_backend.shared.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomPackageService {

    private final CustomPackagePersistencePort customPackagePersistencePort;

    private final CustomPackageMapper customPackageMapper;

    private final UserPersistencePort userPersistencePort;

    private final TestPersistencePort testPersistencePort;

    private final TestMapper testMapper;

    public CustomPackageResponseDto createCustomPackage(CustomPackageRequestDto request) {

        if (ObjectUtils.isEmpty(request))
            throw new InvalidRequestException("Request must not be null or empty");

        UserEntity patient = userPersistencePort.findById(request.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        log.info("Creating a new custom package with name: {}", request.getName());

        CustomPackageEntity customPackage = new CustomPackageEntity();
        customPackage.setPatient(patient);
        customPackage.setName(TestCustomName.valueOf(request.getName()));
        CustomPackageEntity savedPackage = customPackagePersistencePort.save(customPackage);

        List<TestEntity> savedTestEntities = request.getTests()
                .stream()
                .map(testDto -> {
                    TestEntity testEntity = new TestEntity();
                    testEntity.setUsers(patient);
                    testEntity.setCustomPackage(savedPackage);
                    testEntity.setName(testDto.getName());
                    testEntity.setResult(String.valueOf(testDto.getResult()));
                    testEntity.setRange(TestRange.valueOf(testDto.getRange()));
                    return testEntity;
                })
                .map(testPersistencePort::save)
                .toList();

        List<TestResponseDto> testResponseDtos = testMapper.toDtoList(savedTestEntities);
        CustomPackageResponseDto responseDto = customPackageMapper.toDto(savedPackage);
        responseDto.setTests(testResponseDtos);

        log.info("Custom package created successfully with ID: {}", savedPackage.getId());
        return responseDto;
    }
}

