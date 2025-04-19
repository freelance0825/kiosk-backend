package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;

import java.util.List;
import java.util.Optional;

public interface CustomPackagePersistencePort {

    CustomPackageEntity save(CustomPackageEntity customPackageEntity);

    List<CustomPackageEntity> findAll();

    Optional<CustomPackageEntity> findById(Long id);
}
