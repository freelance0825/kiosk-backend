package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;
import com.freelance.kiosk_backend.domain.repository.AppointmentRepository;
import com.freelance.kiosk_backend.infrastructure.port.AppointmentPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class AppointmentPersistenceAdapter implements AppointmentPersistencePort {

    private final AppointmentRepository appointmentRepository;

    @Override
    public AppointmentEntity save(AppointmentEntity appointment) {
        return appointmentRepository.save(appointment);
    }

    @Override
    public Optional<AppointmentEntity> findById(Long id) {
        return appointmentRepository.findById(id);
    }

    @Override
    public List<AppointmentEntity> findByPatientId(Long patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }

    @Override
    public List<AppointmentEntity> findByDoctorId(Long doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    @Override
    public boolean existsById(Long id) {
        return appointmentRepository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        appointmentRepository.deleteById(id);
    }

    @Override
    public List<AppointmentEntity> findAll() {
        return appointmentRepository.findAll();
    }

}
