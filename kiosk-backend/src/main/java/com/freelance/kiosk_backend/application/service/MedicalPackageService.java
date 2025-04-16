package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageResponseDto;
import com.freelance.kiosk_backend.application.dto.test.TestResponseDto;
import com.freelance.kiosk_backend.application.dto.test.enums.TestMedicalName;
import com.freelance.kiosk_backend.application.dto.test.enums.TestRange;
import com.freelance.kiosk_backend.domain.entity.MedicalPackageEntity;
import com.freelance.kiosk_backend.domain.entity.TestEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.MedicalPackageMapper;
import com.freelance.kiosk_backend.domain.mapper.TestMapper;
import com.freelance.kiosk_backend.infrastructure.port.MedicalPackagePersistencePort;
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
public class MedicalPackageService {

    private final MedicalPackagePersistencePort medicalPackagePersistencePort;

    private final MedicalPackageMapper medicalPackageMapper;

    private final UserPersistencePort userPersistencePort;

    private final TestPersistencePort testPersistencePort;

    private final TestMapper testMapper;

    public MedicalPackageResponseDto createMedicalPackage(MedicalPackageRequestDto request) {
        if (ObjectUtils.isEmpty(request))
            throw new InvalidRequestException("Request must not be null or empty");

        UserEntity patient = userPersistencePort.findById(request.getPatientId())
                .orElseThrow(() -> new PatientNotFoundException("Patient not found"));

        log.info("Creating a new custom package with name: {}", request.getName());

        MedicalPackageEntity medicalPackage = new MedicalPackageEntity();
        medicalPackage.setPatient(patient);
        medicalPackage.setName(TestMedicalName.valueOf(request.getName()));
        MedicalPackageEntity savedPackage = medicalPackagePersistencePort.save(medicalPackage);

        List<TestEntity> savedTestEntities = request.getTests()
                .stream()
                .map(testDto -> {
                    TestEntity testEntity = new TestEntity();
                    testEntity.setUsers(patient);
                    testEntity.setMedicalPackage(savedPackage);
                    testEntity.setName(testDto.getName());
                    testEntity.setResult(String.valueOf(testDto.getResult()));
                    testEntity.setRange(TestRange.valueOf(testDto.getRange()));
                    return testEntity;
                })
                .map(testPersistencePort::save)
                .toList();

        List<TestResponseDto> testResponseDtos = testMapper.toDtoList(savedTestEntities);
        MedicalPackageResponseDto responseDto = medicalPackageMapper.toDto(savedPackage);
        responseDto.setTests(testResponseDtos);

        log.info("Medical package created successfully with ID: {}", savedPackage.getId());
        return responseDto;
    }
}
