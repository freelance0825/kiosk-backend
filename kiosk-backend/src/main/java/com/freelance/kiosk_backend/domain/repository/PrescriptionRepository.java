package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Long> {

    List<PrescriptionEntity> findByPostConsultationId(Long postConsultationId);
}
