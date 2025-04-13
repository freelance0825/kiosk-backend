package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.NotificationEntity;

import java.util.List;
import java.util.Optional;

public interface NotificationPersistencePort {

    NotificationEntity save(NotificationEntity notificationEntity);

    Optional<NotificationEntity> findByAppointmentId(Long appointmentId);

    List<NotificationEntity> findByAppointmentPatientId(Long patientId);
}
