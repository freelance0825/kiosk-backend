package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.TestEntity;

import java.util.List;

public interface TestPersistencePort {

    TestEntity save(TestEntity testEntity);

    List<TestEntity> findByPatientId(Long patientId);
}
