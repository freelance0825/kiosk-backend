package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;

public interface CustomPackagePersistencePort {

    CustomPackageEntity save(CustomPackageEntity customPackageEntity);
}
