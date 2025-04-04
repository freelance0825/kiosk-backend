package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.MedicalPackageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalPackageRepository extends JpaRepository<MedicalPackageEntity, Long> {
}
