package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.MedicalPackageEntity;
import com.freelance.kiosk_backend.domain.repository.MedicalPackageRepository;
import com.freelance.kiosk_backend.infrastructure.port.MedicalPackagePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MedicalPackageAdapter implements MedicalPackagePersistencePort {

    private final MedicalPackageRepository medicalPackageRepository;

    @Override
    public MedicalPackageEntity save(MedicalPackageEntity medicalPackageEntity) {
        return medicalPackageRepository.save(medicalPackageEntity);
    }

    @Override
    public List<MedicalPackageEntity> findAll() {
        return medicalPackageRepository.findAll();
    }

    @Override
    public Optional<MedicalPackageEntity> findById(Long id) {
        return medicalPackageRepository.findById(id);
    }

}
