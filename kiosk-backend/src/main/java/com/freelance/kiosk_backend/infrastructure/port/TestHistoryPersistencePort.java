package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;

import java.util.List;

public interface TestHistoryPersistencePort {

    TestHistoryEntity save(TestHistoryEntity testHistoryEntity);

    List<TestHistoryEntity> findByPatientId(Long patientId);

    List<TestHistoryEntity> findByMedicalPackageId(Long medicalPackageId);
}
