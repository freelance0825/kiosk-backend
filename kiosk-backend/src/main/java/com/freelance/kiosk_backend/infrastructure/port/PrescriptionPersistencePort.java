package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;

import java.util.List;

public interface PrescriptionPersistencePort {

    PrescriptionEntity save(PrescriptionEntity prescription);

    // Find prescriptions by postConsultation ID
    List<PrescriptionEntity> findByPostConsultationId(Long postConsultationId);
}
