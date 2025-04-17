package com.freelance.kiosk_backend.domain.entity;

import com.freelance.kiosk_backend.application.dto.test.enums.TestRange;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "test")
public class TestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "custom_package_id")
    private CustomPackageEntity customPackage;

    @ManyToOne
    @JoinColumn(name = "medical_package_id")
    private MedicalPackageEntity medicalPackage;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String result;

    @Enumerated(EnumType.STRING)
    private TestRange range;

    @Column(name = "created_at" ,nullable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

}