package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;
import com.freelance.kiosk_backend.domain.repository.PrescriptionRepository;
import com.freelance.kiosk_backend.infrastructure.port.PrescriptionPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class PrescriptionPersistenceAdapter implements PrescriptionPersistencePort {

    private final PrescriptionRepository prescriptionRepository;

    @Override
    public PrescriptionEntity save(PrescriptionEntity prescription) {
        return prescriptionRepository.save(prescription);
    }

    @Override
    public List<PrescriptionEntity> findByPostConsultationId(Long postConsultationId){
        return prescriptionRepository.findByPostConsultationId(postConsultationId);
    }


}
