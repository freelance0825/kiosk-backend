package com.freelance.kiosk_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "prescription")
public class PrescriptionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String morning;

    private String afternoon;

    private String night;

    private String notes;

    private String duration;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "consult_id", nullable = false)
    private PostConsultationEntity postConsultation;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;

}
