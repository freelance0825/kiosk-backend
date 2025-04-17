package com.freelance.kiosk_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_booked")
    private Boolean isBooked = false;

    @Column(name = "is_rescheduled")
    private Boolean isRescheduled = false;

    @Column(name = "is_cancelled")
    private Boolean isCancelled = false;

    @Column(name = "appt_date_time")
    private OffsetDateTime apptDateTime;

    @Column(name = "appt_users_name")
    private String apptUserName;

    @Column(name = "appt_doctor_name")
    private String apptDoctorName;

    @Column(name = "appt_doctor_specialization")
    private String apptDoctorSpecialization;

    @Column(name = "created_at")
    @UpdateTimestamp
    private OffsetDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appt_doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appt_users_id", nullable = false)
    private UserEntity users;

}
