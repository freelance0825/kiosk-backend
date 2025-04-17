package com.freelance.kiosk_backend.domain.entity;

import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="post_consultation")
public class PostConsultationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;

    @Getter
    @Setter
    @Transient // This tells JPA to ignore this field during persistence
    private List<MedicineDto> medicines;

    @Column(name = "date_time",nullable = false)
    private OffsetDateTime dateTime;

    private String diagnosis;

    private String suggestions;

    private String followUpDate;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String signature; // Base64 signature image
}
