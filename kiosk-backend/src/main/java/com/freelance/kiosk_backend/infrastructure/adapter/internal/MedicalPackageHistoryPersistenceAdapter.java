package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.MedicalPackageHistoryEntity;
import com.freelance.kiosk_backend.domain.repository.MedicalPackageHistoryRepository;
import com.freelance.kiosk_backend.infrastructure.port.MedicalPackageHistoryPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class MedicalPackageHistoryPersistenceAdapter implements MedicalPackageHistoryPersistencePort {

    private final MedicalPackageHistoryRepository medicalPackageHistoryRepository;

    @Override
    public MedicalPackageHistoryEntity save(MedicalPackageHistoryEntity medicalPackageHistoryEntity) {
        return medicalPackageHistoryRepository.save(medicalPackageHistoryEntity);
    }

    @Override
    public List<MedicalPackageHistoryEntity> findAll() {
        return medicalPackageHistoryRepository.findAll();
    }

    @Override
    public Optional<MedicalPackageHistoryEntity> findById(Long id) {
        return medicalPackageHistoryRepository.findById(id);
    }

}
