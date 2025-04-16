package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.test.MedicalTestResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestEntity;
import com.freelance.kiosk_backend.domain.mapper.MedicalTestMapper;
import com.freelance.kiosk_backend.infrastructure.port.TestPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalTestService {

    private final TestPersistencePort testPersistencePort;

    private final MedicalTestMapper medicalTestMapper;

    public List<MedicalTestResponseDto> getMedicalTestByPatientId(Long patientId) {
        List<TestEntity> testEntities = testPersistencePort.findByPatientId(patientId);

        List<MedicalTestResponseDto> dtoList = testEntities.stream()
                .sorted(Comparator.comparing(TestEntity::getCreatedAt).reversed())
                .map(medicalTestMapper::toDto)
                .toList();

        return dtoList;
    }
}