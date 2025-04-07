package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Long> {

    Optional<List<DoctorEntity>> findAllByStatus(String status);

}
