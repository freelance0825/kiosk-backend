package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.MedicalPackageHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalPackageHistoryRepository extends JpaRepository<MedicalPackageHistoryEntity, Long> {
}
