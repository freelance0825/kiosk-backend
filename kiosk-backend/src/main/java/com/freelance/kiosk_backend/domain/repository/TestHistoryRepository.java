package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestHistoryRepository extends JpaRepository<TestHistoryEntity, Long> {

    List<TestHistoryEntity> findByUsers_Id(Long patientId);

    List<TestHistoryEntity> findByMedicalPackage_Id(Long medicalPackageId);

}


