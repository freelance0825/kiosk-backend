package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import com.freelance.kiosk_backend.application.dto.appointment.PostConsultationForAppointmentDto;
import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import com.freelance.kiosk_backend.domain.entity.PrescriptionEntity;
import com.freelance.kiosk_backend.domain.mapper.NotificationMapper;
import com.freelance.kiosk_backend.infrastructure.port.NotificationPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.PrescriptionPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationService {

    private final NotificationPersistencePort notificationPersistencePort;

    private final PrescriptionPersistencePort prescriptionPersistencePort;

    private final NotificationMapper notificationMapper;

    public List<NotificationResponseDto> getNotificationsByAppointmentPatientId(Long patientId) {
        List<NotificationEntity> notificationList = notificationPersistencePort.findByAppointmentPatientId(patientId);
        List<NotificationResponseDto> responseList = notificationMapper.toDtoList(notificationList);

        for (NotificationResponseDto dto : responseList) {
            AppointmentResponseDto appointment = dto.getAppointment();
            if (appointment != null && appointment.getPostConsultation() != null) {
                populateMedicines(appointment.getPostConsultation());
            }
        }

        return responseList;
    }

    private void populateMedicines(PostConsultationForAppointmentDto postConsultation) {
        if (postConsultation.getId() != null) {
            List<PrescriptionEntity> prescriptions = prescriptionPersistencePort.findByPostConsultationId(postConsultation.getId());
            List<MedicineDto> medicines = prescriptions.stream()
                    .map(notificationMapper::toMedicineDto)
                    .collect(Collectors.toList());

            postConsultation.setMedicines(medicines);
        }
    }
}
