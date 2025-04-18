package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.TestEntity;
import com.freelance.kiosk_backend.domain.repository.TestRepository;
import com.freelance.kiosk_backend.infrastructure.port.TestPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class TestPersistenceAdapter implements TestPersistencePort {

    private final TestRepository testRepository;

    @Override
    public TestEntity save(TestEntity testEntity) {
        return testRepository.save(testEntity);
    }

    @Override
    public List<TestEntity> findByPatientId(Long patientId) {
        return testRepository.findByUsers_Id(patientId);
    }

    @Override
    public List<TestEntity> findByMedicalPackageId(Long medicalPackageId) {
        return testRepository.findByMedicalPackage_Id(medicalPackageId);
    }
}
