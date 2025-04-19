package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.custompackagehistory.CustomPackageHistoryRequestDto;
import com.freelance.kiosk_backend.application.dto.custompackagehistory.CustomPackageHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.testhistory.enums.TestCustomName;
import com.freelance.kiosk_backend.application.dto.testhistory.enums.TestRange;
import com.freelance.kiosk_backend.domain.entity.CustomPackageHistoryEntity;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.CustomPackageHistoryMapper;
import com.freelance.kiosk_backend.domain.mapper.TestHistoryMapper;
import com.freelance.kiosk_backend.infrastructure.port.CustomPackageHistoryPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.TestHistoryPersistencePort;
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
public class CustomPackageHistoryService {

    private final CustomPackageHistoryPersistencePort customPackageHistoryPersistencePort;

    private final CustomPackageHistoryMapper customPackageHistoryMapper;

    private final UserPersistencePort userPersistencePort;

    private final TestHistoryPersistencePort testHistoryPersistencePort;

    private final TestHistoryMapper testHistoryMapper;

    public CustomPackageHistoryResponseDto createCustomPackageHistory(CustomPackageHistoryRequestDto request) {

        if (ObjectUtils.isEmpty(request))
            throw new InvalidRequestException("Request must not be null or empty");

        UserEntity patient = userPersistencePort.findById(request.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        log.info("Creating a new custom package with name: {}", request.getName());

        CustomPackageHistoryEntity customPackage = new CustomPackageHistoryEntity();
        customPackage.setPatient(patient);
        customPackage.setName(TestCustomName.valueOf(request.getName()));
        CustomPackageHistoryEntity savedPackage = customPackageHistoryPersistencePort.save(customPackage);

        List<TestHistoryEntity> savedTestEntities = request.getTests()
                .stream()
                .map(testDto -> {
                    TestHistoryEntity testHistoryEntity = new TestHistoryEntity();
                    testHistoryEntity.setUsers(patient);
                    testHistoryEntity.setCustomPackage(savedPackage);
                    testHistoryEntity.setName(testDto.getName());
                    testHistoryEntity.setResult(String.valueOf(testDto.getResult()));
                    testHistoryEntity.setRange(TestRange.valueOf(testDto.getRange()));
                    return testHistoryEntity;
                })
                .map(testHistoryPersistencePort::save)
                .toList();

        List<TestHistoryResponseDto> testHistoryResponseDtos = testHistoryMapper.toDtoList(savedTestEntities);
        CustomPackageHistoryResponseDto responseDto = customPackageHistoryMapper.toDto(savedPackage);
        responseDto.setTests(testHistoryResponseDtos);

        log.info("Custom package created successfully with ID: {}", savedPackage.getId());
        return responseDto;
    }
}

