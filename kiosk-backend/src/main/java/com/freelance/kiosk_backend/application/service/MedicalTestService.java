package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.testhistory.MedicalTestHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import com.freelance.kiosk_backend.domain.mapper.MedicalTestMapper;
import com.freelance.kiosk_backend.infrastructure.port.TestHistoryPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalTestService {

    private final TestHistoryPersistencePort testHistoryPersistencePort;

    private final MedicalTestMapper medicalTestMapper;

    public List<MedicalTestHistoryResponseDto> getMedicalTestHistoryByPatientId(Long patientId) {
        List<TestHistoryEntity> testEntities = testHistoryPersistencePort.findByPatientId(patientId);

        List<MedicalTestHistoryResponseDto> dtoList = testEntities.stream()
                .sorted(Comparator.comparing(TestHistoryEntity::getCreatedAt).reversed())
                .map(medicalTestMapper::toDto)
                .toList();

        return dtoList;
    }
}