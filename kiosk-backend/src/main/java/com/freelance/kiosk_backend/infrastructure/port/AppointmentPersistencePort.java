package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;

import java.util.List;
import java.util.Optional;

public interface AppointmentPersistencePort {

    AppointmentEntity save(AppointmentEntity appointmentEntity);

    Optional<AppointmentEntity> findById(Long id);

    List<AppointmentEntity> findByPatientIdWithPostConsultation(Long patientId);

    List<AppointmentEntity> findByDoctorId(Long doctorId);

    boolean existsById(Long id);

    void deleteById(Long id);

    List<AppointmentEntity> findAll();

}
