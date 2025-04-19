package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.DoctorEntity;
import com.freelance.kiosk_backend.domain.repository.DoctorRepository;
import com.freelance.kiosk_backend.infrastructure.port.DoctorPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class DoctorPersistenceAdapter implements DoctorPersistencePort {

    private final DoctorRepository doctorRepository;

    @Override
    public DoctorEntity save(DoctorEntity doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public Optional<DoctorEntity> findById(Long id) {
        return doctorRepository.findById(id);
    }

    @Override
    public List<DoctorEntity> findAll() {
        return doctorRepository.findAll();
    }


    @Override
    public Optional<List<DoctorEntity>> findAllByStatus(String status) {
        return doctorRepository.findAllByStatus(status);
    }

    @Override
    public boolean existsById(Long id) {
        return doctorRepository.existsById(id);
    }

}
