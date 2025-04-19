package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.medicalpackagehistory.MedicalPackageHistoryRequestDto;
import com.freelance.kiosk_backend.application.dto.medicalpackagehistory.MedicalPackageHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.testhistory.enums.TestMedicalName;
import com.freelance.kiosk_backend.application.dto.testhistory.enums.TestRange;
import com.freelance.kiosk_backend.domain.entity.MedicalPackageHistoryEntity;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.MedicalPackageHistoryMapper;
import com.freelance.kiosk_backend.domain.mapper.TestHistoryMapper;
import com.freelance.kiosk_backend.infrastructure.port.MedicalPackageHistoryPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.TestHistoryPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.UserPersistencePort;
import com.freelance.kiosk_backend.shared.exception.InvalidRequestException;
import com.freelance.kiosk_backend.shared.exception.PatientNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalPackageHistoryService {

    private final MedicalPackageHistoryPersistencePort medicalPackageHistoryPersistencePort;

    private final MedicalPackageHistoryMapper medicalPackageHistoryMapper;

    private final UserPersistencePort userPersistencePort;

    private final TestHistoryPersistencePort testHistoryPersistencePort;

    private final TestHistoryMapper testHistoryMapper;

    public MedicalPackageHistoryResponseDto createMedicalPackageHistory(MedicalPackageHistoryRequestDto request) {
        if (ObjectUtils.isEmpty(request))
            throw new InvalidRequestException("Request must not be null or empty");

        UserEntity patient = userPersistencePort.findById(request.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        log.info("Creating a new custom package with name: {}", request.getName());

        MedicalPackageHistoryEntity medicalPackage = new MedicalPackageHistoryEntity();
        medicalPackage.setPatient(patient);
        medicalPackage.setName(TestMedicalName.valueOf(request.getName()));
        MedicalPackageHistoryEntity savedPackage = medicalPackageHistoryPersistencePort.save(medicalPackage);

        List<TestHistoryEntity> savedTestEntities = request.getTests()
                .stream()
                .map(testDto -> {
                    TestHistoryEntity testHistoryEntity = new TestHistoryEntity();
                    testHistoryEntity.setUsers(patient);
                    testHistoryEntity.setMedicalPackage(savedPackage);
                    testHistoryEntity.setName(testDto.getName());
                    testHistoryEntity.setResult(String.valueOf(testDto.getResult()));
                    testHistoryEntity.setRange(TestRange.valueOf(testDto.getRange()));
                    return testHistoryEntity;
                })
                .map(testHistoryPersistencePort::save)
                .toList();

        List<TestHistoryResponseDto> testHistoryResponseDtos = testHistoryMapper.toDtoList(savedTestEntities);
        MedicalPackageHistoryResponseDto responseDto = medicalPackageHistoryMapper.toDto(savedPackage);
        responseDto.setTests(testHistoryResponseDtos);

        log.info("Medical package created successfully with ID: {}", savedPackage.getId());
        return responseDto;
    }

}
