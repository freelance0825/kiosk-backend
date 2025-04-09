package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;

public interface PrescriptionPersistencePort {

    PrescriptionEntity save(PrescriptionEntity prescription);
}
