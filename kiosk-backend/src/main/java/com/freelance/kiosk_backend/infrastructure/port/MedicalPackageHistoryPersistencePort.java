package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.MedicalPackageHistoryEntity;

import java.util.List;
import java.util.Optional;

public interface MedicalPackageHistoryPersistencePort {

    MedicalPackageHistoryEntity save(MedicalPackageHistoryEntity medicalPackageHistoryEntity);

    List<MedicalPackageHistoryEntity> findAll();

    Optional<MedicalPackageHistoryEntity> findById(Long id);
}
