package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;
import com.freelance.kiosk_backend.domain.repository.CustomPackageRepository;
import com.freelance.kiosk_backend.infrastructure.port.CustomPackagePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomPackagePersistenceAdapter implements CustomPackagePersistencePort {

    private final CustomPackageRepository customPackageRepository;

    @Override
    public CustomPackageEntity save(CustomPackageEntity customPackageEntity) {
        return customPackageRepository.save(customPackageEntity);
    }

    @Override
    public List<CustomPackageEntity> findAll() {
        return customPackageRepository.findAll();
    }

    @Override
    public Optional<CustomPackageEntity> findById(Long id) {
        return customPackageRepository.findById(id);
    }

}
