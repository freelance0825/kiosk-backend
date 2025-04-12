package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findByDoctorId(Long doctorId);

    @Query("SELECT a FROM AppointmentEntity a LEFT JOIN FETCH a.postConsultation pc WHERE a.patient.id = :patientId")
    List<AppointmentEntity> findByPatientIdWithPostConsultation(@Param("patientId") Long patientId);

}
