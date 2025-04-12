package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentRequestDto;
import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import com.freelance.kiosk_backend.application.dto.appointment.PostConsultationForAppointmentDto;
import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;
import com.freelance.kiosk_backend.domain.entity.DoctorEntity;
import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.AppointmentMapper;
import com.freelance.kiosk_backend.infrastructure.port.AppointmentPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.DoctorPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.PrescriptionPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.UserPersistencePort;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentPersistencePort appointmentPersistencePort;

    private final PrescriptionPersistencePort prescriptionPersistencePort;

    private final AppointmentMapper appointmentMapper;

    private final DoctorPersistencePort doctorPersistencePort;

    private final UserPersistencePort userPersistencePort;

    public AppointmentResponseDto saveAppointmentWithImage(AppointmentRequestDto appointmentDto,
                                                           Long currentPatient, Long chosenDoctor) throws IOException {
        log.info("Received new appointment: {}", appointmentDto);

        DoctorEntity doctor = doctorPersistencePort.findById(chosenDoctor)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        UserEntity patient = userPersistencePort.findById(currentPatient)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        try {
            AppointmentEntity appointment = createAppointment(appointmentDto, doctor, patient);
            AppointmentEntity saved = appointmentPersistencePort.save(appointment);
            return appointmentMapper.toDto(saved);
        } catch (Exception e) {
            log.error("Failed to save appointment", e);
            throw new IOException(e.getMessage());
        }
    }

    public AppointmentResponseDto updateAppointment(Long id, AppointmentRequestDto appointmentDto) throws IOException {
        log.info("Updating appointment with ID: {}", id);
        try {
            AppointmentEntity existingAppointment = appointmentPersistencePort.findById(id)
                    .orElseThrow(() -> new IOException("Appointment not found for ID: " + id));

            // Update fields only if present in the request
            appointmentMapper.updateAppointmentFromDto(appointmentDto, existingAppointment);

            // Save the updated appointment entity
            AppointmentEntity updatedAppointment = appointmentPersistencePort.save(existingAppointment);
            log.info("Successfully updated appointment: {}", updatedAppointment.getName());

            return appointmentMapper.toDto(updatedAppointment);

        } catch (Exception e) {
            log.error("Error updating appointment: {}", e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    public AppointmentResponseDto getAppointmentById(Long id) {
        AppointmentEntity appointmentEntity = appointmentPersistencePort.findById(id).orElse(null);
        AppointmentResponseDto responseDto = appointmentMapper.toDto(appointmentEntity);

        // Manually populate the medicines for the postConsultation in the response
        if (responseDto != null && responseDto.getPostConsultation() != null) {
            populateMedicines(responseDto.getPostConsultation());
        }

        return responseDto;
    }


    // Fetch appointments by patient ID
    public List<AppointmentResponseDto> getAppointmentsByPatientId(Long patientId) {
        List<AppointmentEntity> appointmentList = appointmentPersistencePort.findByPatientIdWithPostConsultation(patientId);
        List<AppointmentResponseDto> responseDtos = appointmentMapper.toDtoList(appointmentList);

        // Manually populate the medicines for each postConsultation in the response
        for (AppointmentResponseDto dto : responseDtos) {
            if (dto.getPostConsultation() != null) {
                populateMedicines(dto.getPostConsultation());
            }
        }

        return responseDtos;
    }

    public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {
        List<AppointmentEntity> appointmentList = appointmentPersistencePort.findByDoctorId(doctorId);
        List<AppointmentResponseDto> responseDtos =  appointmentMapper.toDtoList(appointmentList);
        // Manually populate the medicines for each postConsultation in the response
        for (AppointmentResponseDto dto : responseDtos) {
            if (dto.getPostConsultation() != null) {
                populateMedicines(dto.getPostConsultation());
            }
        }

        return responseDtos;
    }

    @Transactional
    public void deleteAppointment(Long id) throws IOException {
        log.info("Deleting appointment with ID: {}", id);
        try {
            AppointmentEntity existingAppointment = appointmentPersistencePort.findById(id).orElse(null);
            if (appointmentPersistencePort.existsById(id)) {
                appointmentPersistencePort.deleteById(id);
            }

            log.info("Appointment with ID: {} has been deleted successfully", id);
        } catch (Exception e) {
            log.error("Error deleting appointment with ID: {}: {}", id, e.getMessage());
            throw new IOException("Error deleting appointment: " + e.getMessage());
        }
    }

    public List<AppointmentResponseDto> getAllAppointments() {
        List<AppointmentEntity> appointmentList = appointmentPersistencePort.findAll();
        return appointmentMapper.toDtoList(appointmentList);
    }

    private AppointmentEntity createAppointment(AppointmentRequestDto dto, DoctorEntity doctor, UserEntity patient) {
        AppointmentEntity entity = appointmentMapper.toEntity(dto);
        entity.setDoctor(doctor);
        entity.setPatient(patient);
        entity.setName(doctor.getName());
        entity.setSpecialization(doctor.getSpecialization());
        entity.setImageBase64(doctor.getImageBase64());
        return entity;
    }

    // Manually populate medicines from PrescriptionEntity for each PostConsultation
    private void populateMedicines(PostConsultationForAppointmentDto postConsultation) {
        // Assuming PostConsultationEntity has a list of prescriptions
        if (postConsultation != null && postConsultation.getId() != null) {
            // Fetch the prescriptions related to the current postConsultation
            List<PrescriptionEntity> prescriptions = prescriptionPersistencePort.findByPostConsultationId(postConsultation.getId());

            // Map the prescriptions to MedicineDto
            List<MedicineDto> medicines = prescriptions.stream()
                    .map(appointmentMapper::toMedicineDto)
                    .collect(Collectors.toList());

            // Set the medicines in the PostConsultation DTO
            postConsultation.setMedicines(medicines);
        }
    }

}
