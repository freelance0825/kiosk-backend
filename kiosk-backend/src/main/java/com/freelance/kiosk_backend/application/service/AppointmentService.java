package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.appointment.*;
import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.domain.entity.*;
import com.freelance.kiosk_backend.domain.mapper.AppointmentMapper;
import com.freelance.kiosk_backend.infrastructure.port.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppointmentService {

    private final AppointmentPersistencePort appointmentPersistencePort;

    private final PrescriptionPersistencePort prescriptionPersistencePort;

    private final NotificationPersistencePort notificationPersistencePort;

    private final AppointmentMapper appointmentMapper;

    private final DoctorPersistencePort doctorPersistencePort;

    private final UserPersistencePort userPersistencePort;

    public AppointmentResponseDto createAppointment(AppointmentRequestDto createAppointment) {
        log.info("Received new appointment: {}", createAppointment);

        DoctorEntity doctor = doctorPersistencePort.findById(createAppointment.getDoctorId())
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found"));
        UserEntity patient = userPersistencePort.findById(createAppointment.getPatientId())
                .orElseThrow(() -> new IllegalArgumentException("Patient not found"));

        // Convert appointmentDto.dateTime to OffsetDateTime in UTC
        OffsetDateTime appointmentDateTime = createAppointment.getDateTime().withOffsetSameInstant(ZoneOffset.UTC);

        // Check if dateTime is already booked by another patient (in UTC)
        boolean isAlreadyBooked = appointmentPersistencePort.existsByDateTimeAndDoctorId(appointmentDateTime, createAppointment.getDoctorId());
        if (isAlreadyBooked) {
            throw new RuntimeException("Selected date and time is already booked.");
        }

        try {
            AppointmentEntity appointment = createAppointment(createAppointment, doctor, patient, appointmentDateTime);
            AppointmentEntity saved = appointmentPersistencePort.save(appointment);

            // Use the helper method to create the notification for the new appointment
            manageNotification(saved, true, false, false);

            return appointmentMapper.toDto(saved);
        } catch (Exception e) {
            log.error("Failed to save appointment", e);
            throw new RuntimeException("Failed to create appointment: " + e.getMessage());
        }
    }

    public AppointmentResponseDto updateAppointment(Long id, UpdateAppointmentRequestDto updatedAppointment) {
        log.info("Updating appointment with ID: {}", id);

        try {
            AppointmentEntity existingAppointment = appointmentPersistencePort.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found for ID: " + id));

            OffsetDateTime oldDateTime = existingAppointment.getDateTime();
            OffsetDateTime newDateTime = updatedAppointment.getDateTime().withOffsetSameInstant(ZoneOffset.UTC);

            // Only check for conflict if the dateTime is actually changing
            if (!oldDateTime.equals(newDateTime)) {
                Long doctorId = existingAppointment.getDoctor().getId();
                if (appointmentPersistencePort.existsByDateTimeAndDoctorId(newDateTime, doctorId)) {
                    throw new RuntimeException("Selected new date and time is already booked.");
                }
                existingAppointment.setDateTime(newDateTime);
            }

            AppointmentEntity savedAppointment = appointmentPersistencePort.save(existingAppointment);

            boolean isRescheduled = !oldDateTime.equals(savedAppointment.getDateTime());
            if (isRescheduled) {
                manageNotification(savedAppointment, false, true, false);
                log.info("Notification for appointment ID {} updated due to rescheduling", id);
            }

            log.info("Successfully updated appointment with ID: {}", id);
            return appointmentMapper.toDto(savedAppointment);
        } catch (Exception e) {
            log.error("Error updating appointment", e);
            throw new RuntimeException("Failed to update appointment: " + e.getMessage());
        }
    }

    @Transactional
    public void deleteAppointment(Long id) {
        log.info("Deleting appointment with ID: {}", id);

        try {
            AppointmentEntity existingAppointment = appointmentPersistencePort.findById(id)
                    .orElseThrow(() -> new RuntimeException("Appointment not found for ID: " + id));

            // Use the helper method to update the notification when the appointment is deleted
            manageNotification(existingAppointment, false, false, true);

            // Delete the appointment
            appointmentPersistencePort.deleteById(id);

            log.info("Appointment with ID: {} has been deleted successfully.", id);
        } catch (Exception e) {
            log.error("Error deleting appointment with ID: {}", id, e);
            throw new RuntimeException("Failed to delete appointment: " + e.getMessage());
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

    public List<AppointmentResponseDto> getAppointmentsByPatientId(Long patientId) {
        List<AppointmentEntity> appointmentList = appointmentPersistencePort.findByPatientId(patientId);
        List<AppointmentResponseDto> responseList = appointmentMapper.toDtoList(appointmentList);

        for (AppointmentResponseDto dto : responseList) {
            if (dto.getPostConsultation() != null) {
                populateMedicines(dto.getPostConsultation());
            }
        }

        return responseList;
    }

    public List<AppointmentResponseDto> getAppointmentsByDoctorId(Long doctorId) {
        List<AppointmentEntity> appointmentList = appointmentPersistencePort.findByDoctorId(doctorId);
        List<AppointmentResponseDto> responseList = appointmentMapper.toDtoList(appointmentList);

        for (AppointmentResponseDto dto : responseList) {
            if (dto.getPostConsultation() != null) {
                populateMedicines(dto.getPostConsultation());
            }
        }

        return responseList;
    }

    public List<AppointmentResponseDto> getAllAppointments() {
        List<AppointmentEntity> appointmentList = appointmentPersistencePort.findAll();
        return appointmentMapper.toDtoList(appointmentList);
    }

    public AvailableTimeslotResponseDto getAvailableTimeSlotsForDay(Long doctorId, AvailableTimeslotRequestDto request) {
        LocalDate date = request.getDate();
        ZoneId zone = ZoneId.of(request.getZoneId());

        // Get all appointments for the doctor
        List<AppointmentEntity> appointments = appointmentPersistencePort.findByDoctorId(doctorId);

        // Filter to appointments on the requested date in the requested timezone
        List<AppointmentEntity> appointmentsOnDate = appointments.stream()
                .filter(a -> a.getDateTime().atZoneSameInstant(zone).toLocalDate().equals(date))
                .toList();

        // Generate all possible time slots for that day
        List<String> allTimeSlots = generateTimeSlotsForDay(date, zone);

        // Remove time slots that are already booked
        List<String> availableTimeSlots = removeBookedTimeSlots(allTimeSlots, appointmentsOnDate, zone);

        // Build response
        AvailableTimeslotResponseDto responseDto = new AvailableTimeslotResponseDto();
        responseDto.setDate(date.toString());
        responseDto.setAvailableTimeSlots(availableTimeSlots);
        return responseDto;
    }

    private AppointmentEntity createAppointment(AppointmentRequestDto dto, DoctorEntity doctor,
                                                UserEntity patient, OffsetDateTime appointmentDateTime) {
        AppointmentEntity entity = appointmentMapper.toEntity(dto);
        entity.setDoctor(doctor);
        entity.setPatient(patient);
        entity.setDateTime(appointmentDateTime);
        entity.setName(doctor.getName());
        entity.setSpecialization(doctor.getSpecialization());
        entity.setImageBase64(doctor.getImageBase64());
        return entity;
    }

    // Manually populate medicines from PrescriptionEntity for each PostConsultation
    private void populateMedicines(PostConsultationForAppointmentDto postConsultation) {
        if (postConsultation != null && postConsultation.getId() != null) {
            // Fetch the prescriptions related to the current postConsultation
            List<PrescriptionEntity> prescriptions = prescriptionPersistencePort.findByPostConsultationId(postConsultation.getId());

            // Map the prescriptions to MedicineDto
            List<MedicineDto> medicines = prescriptions.stream()
                    .map(appointmentMapper::toMedicineDto)
                    .toList();

            // Set the medicines in the PostConsultation DTO
            postConsultation.setMedicines(medicines);
        }
    }

    private void manageNotification(AppointmentEntity appointment, boolean isBooked, boolean isRescheduled, boolean isCancelled) {
        NotificationEntity notification = notificationPersistencePort.findByAppointmentId(appointment.getId())
                .orElse(new NotificationEntity()); // If not found, create new

        notification.setAppointment(appointment);
        notification.setIsBooked(isBooked);
        notification.setIsRescheduled(isRescheduled);
        notification.setIsCancelled(isCancelled);
        notification.setDoctor(appointment.getDoctor());
        notification.setUsers(appointment.getPatient());
        notification.setApptDateTime(appointment.getDateTime());
        notification.setApptDoctorName(appointment.getDoctor().getName());
        notification.setApptDoctorSpecialization(appointment.getDoctor().getSpecialization());
        notification.setApptUserName(appointment.getPatient().getName());

        notificationPersistencePort.save(notification);
    }

    private List<String> generateTimeSlotsForDay(LocalDate date, ZoneId zone) {
        List<String> timeSlots = new ArrayList<>();
        LocalTime startTime = LocalTime.of(8, 0); // 8:00 AM
        LocalTime endTime = LocalTime.of(17, 0); // 5:00 PM

        while (startTime.isBefore(endTime)) {
            // Combine date and time into ZonedDateTime to format later if needed
            ZonedDateTime zonedDateTime = ZonedDateTime.of(date, startTime, zone);
            timeSlots.add(zonedDateTime.toLocalTime().toString());
            startTime = startTime.plusMinutes(30);
        }

        return timeSlots;
    }


    private List<String> removeBookedTimeSlots(List<String> allTimeSlots, List<AppointmentEntity> appointments, ZoneId zone) {
        List<String> bookedTimeSlots = appointments.stream()
                .map(a -> a.getDateTime().atZoneSameInstant(zone).toLocalTime().toString())
                .toList();

        allTimeSlots.removeAll(bookedTimeSlots);
        return allTimeSlots;
    }

}
