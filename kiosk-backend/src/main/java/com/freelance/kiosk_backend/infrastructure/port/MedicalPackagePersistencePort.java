package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.MedicalPackageEntity;

import java.util.List;
import java.util.Optional;

public interface MedicalPackagePersistencePort {

    MedicalPackageEntity save(MedicalPackageEntity medicalPackageEntity);

    List<MedicalPackageEntity> findAll();

    Optional<MedicalPackageEntity> findById(Long id);
}
