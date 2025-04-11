package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationRequestDto;
import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;
import com.freelance.kiosk_backend.domain.entity.DoctorEntity;
import com.freelance.kiosk_backend.domain.entity.PostConsultationEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.PostConsultationMapper;
import com.freelance.kiosk_backend.infrastructure.port.AppointmentPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.DoctorPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.PostConsultationPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostConsultationService {

    private final PostConsultationPersistencePort postConsultationPersistencePort;

    private final PostConsultationMapper postConsultationMapper;

    private final DoctorPersistencePort doctorPersistencePort;

    private final UserPersistencePort userPersistencePort;

    private final AppointmentPersistencePort appointmentPersistencePort;

    private final PrescriptionService prescriptionService;

    public PostConsultationResponseDto createPostConsultation(PostConsultationRequestDto request) throws IOException {

        // Get required linked entities
        DoctorEntity doctor = doctorPersistencePort.findById(request.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        UserEntity patient = userPersistencePort.findById(request.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));
        AppointmentEntity appointment = appointmentPersistencePort.findById(request.getAppointmentId())
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));

        // Map DTO to Entity using mapper
        PostConsultationEntity postConsultation = postConsultationMapper.mapToEntity(request);
        postConsultation.setDoctor(doctor);
        postConsultation.setPatient(patient);
        postConsultation.setAppointment(appointment);

        // Save the post consultation
        PostConsultationEntity savedPostConsultation = postConsultationPersistencePort.save(postConsultation);

        // Get saved entity again in case you need related objects (optional)
        PostConsultationEntity postConsultationExist = postConsultationPersistencePort.findById(savedPostConsultation.getId())
                .orElseThrow(() -> new IllegalArgumentException("Post Consultation not found"));

        // Convert PostConsultationEntity to PostConsultationResponseDto for setting in MedicineDto
        PostConsultationResponseDto postConsultationDto = postConsultationMapper.toDto(postConsultationExist);

       /* // Directly prepare the prescription medicines list and set the PostConsultationResponseDto
        List<MedicineDto> prescriptionMedicines = request.getMedicines()
                .stream()
                .map(medicineDto -> {
                    medicineDto.setPostConsultation(postConsultationDto);  // Set the PostConsultationResponseDto
                    return medicineDto;
                })
                .toList();*/

        // Prepare the prescription medicines without setting full PostConsultationResponseDto
        List<MedicineDto> prescriptionMedicines = request.getMedicines()
                .stream()
                .map(medicineDto -> {
                    medicineDto.setPostConsultationId(savedPostConsultation.getId()); // just pass the ID
                    return medicineDto;
                })
                .toList();

        // Create and save prescriptions
        for (MedicineDto medicineDto : prescriptionMedicines) {
            prescriptionService.createPrescription(medicineDto);
        }

        // Return DTO using mapper
        return postConsultationMapper.toDto(savedPostConsultation);
    }
}
