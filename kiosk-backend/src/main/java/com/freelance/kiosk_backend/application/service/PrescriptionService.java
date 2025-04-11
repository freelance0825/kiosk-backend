package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.application.dto.prescription.PrescriptionResponseDto;
import com.freelance.kiosk_backend.domain.entity.PostConsultationEntity;
import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;
import com.freelance.kiosk_backend.domain.mapper.PostConsultationMapper;
import com.freelance.kiosk_backend.domain.mapper.PrescriptionMapper;
import com.freelance.kiosk_backend.infrastructure.port.PostConsultationPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.PrescriptionPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrescriptionService {

    private final PrescriptionPersistencePort prescriptionPersistencePort;

    private final PostConsultationPersistencePort postConsultationPersistencePort;

    private final PrescriptionMapper prescriptionMapper;

    private final PostConsultationMapper postConsultationMapper;

    /*public PrescriptionResponseDto createPrescription(MedicineDto medicineDto) throws IOException {

        PrescriptionEntity prescription = prescriptionMapper.toEntity(medicineDto);
        PostConsultationEntity postConsultation = postConsultationMapper.toEntity(medicineDto.getPostConsultation());
        prescription.setPostConsultation(postConsultation);

        log.info("Prescription saved: {}", prescription);

        PrescriptionEntity savedPrescription = prescriptionPersistencePort.save(prescription);
        return prescriptionMapper.toDto(savedPrescription);
    }*/

    public PrescriptionResponseDto createPrescription(MedicineDto medicineDto) throws IOException {

        // Convert MedicineDto to PrescriptionEntity
        PrescriptionEntity prescription = prescriptionMapper.toEntity(medicineDto);

        // Look up PostConsultationEntity by ID
        Long postConsultationId = medicineDto.getPostConsultationId();
        PostConsultationEntity postConsultation = postConsultationPersistencePort.findById(postConsultationId)
                .orElseThrow(() -> new IllegalArgumentException("Post Consultation not found for ID: " + postConsultationId));

        // Link prescription to its parent consultation
        prescription.setPostConsultation(postConsultation);

        log.info("Prescription saved: {}", prescription);

        // Save and return
        PrescriptionEntity savedPrescription = prescriptionPersistencePort.save(prescription);
        return prescriptionMapper.toDto(savedPrescription);
    }

}
