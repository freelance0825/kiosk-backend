package com.freelance.kiosk_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name="appointment")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "consult_id", nullable = true)
    private PostConsultationEntity postConsultation;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;

    @Column(name = "date_time",nullable = false)
    private OffsetDateTime dateTime;

    private String name;

    private String specialization;

    private String healthComplaints;
}
