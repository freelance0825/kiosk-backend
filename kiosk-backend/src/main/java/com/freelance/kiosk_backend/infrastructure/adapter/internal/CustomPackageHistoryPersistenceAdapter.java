package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.CustomPackageHistoryEntity;
import com.freelance.kiosk_backend.domain.repository.CustomPackageHistoryRepository;
import com.freelance.kiosk_backend.infrastructure.port.CustomPackageHistoryPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class CustomPackageHistoryPersistenceAdapter implements CustomPackageHistoryPersistencePort {

    private final CustomPackageHistoryRepository customPackageHistoryRepository;

    @Override
    public CustomPackageHistoryEntity save(CustomPackageHistoryEntity customPackageHistoryEntity) {
        return customPackageHistoryRepository.save(customPackageHistoryEntity);
    }

}
