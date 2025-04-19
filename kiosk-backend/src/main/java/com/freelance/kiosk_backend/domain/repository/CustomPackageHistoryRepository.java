package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.CustomPackageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPackageHistoryRepository extends JpaRepository<CustomPackageHistoryEntity, Long> {
}
