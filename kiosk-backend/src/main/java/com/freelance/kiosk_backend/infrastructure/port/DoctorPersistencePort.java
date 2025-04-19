package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.DoctorEntity;

import java.util.List;
import java.util.Optional;

public interface DoctorPersistencePort {

    DoctorEntity save(DoctorEntity doctor);

    Optional<DoctorEntity> findById(Long id);

    List<DoctorEntity> findAll();

    Optional<List<DoctorEntity>> findAllByStatus(String status);

    boolean existsById(Long id);

}
