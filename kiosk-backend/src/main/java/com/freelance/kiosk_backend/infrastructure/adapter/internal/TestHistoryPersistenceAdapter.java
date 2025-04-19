package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import com.freelance.kiosk_backend.domain.repository.TestHistoryRepository;
import com.freelance.kiosk_backend.infrastructure.port.TestHistoryPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestHistoryPersistenceAdapter implements TestHistoryPersistencePort {

    private final TestHistoryRepository testHistoryRepository;

    @Override
    public TestHistoryEntity save(TestHistoryEntity testHistoryEntity) {
        return testHistoryRepository.save(testHistoryEntity);
    }

    @Override
    public List<TestHistoryEntity> findByPatientId(Long patientId) {
        return testHistoryRepository.findByUsers_Id(patientId);
    }

    @Override
    public List<TestHistoryEntity> findByMedicalPackageId(Long medicalPackageId) {
        return testHistoryRepository.findByMedicalPackage_Id(medicalPackageId);
    }
}
