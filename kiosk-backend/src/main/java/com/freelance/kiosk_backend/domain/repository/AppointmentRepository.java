package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findByPatientId(Long doctorId);

    List<AppointmentEntity> findByDoctorId(Long doctorId);

}
