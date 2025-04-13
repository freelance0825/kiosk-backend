package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    Optional<NotificationEntity> findByAppointmentId(Long appointmentId);

    //Traverses through appointment -> patient -> id
    List<NotificationEntity> findByAppointment_Patient_Id(Long patientId);
}
