package com.freelance.kiosk_backend.domain.entity;

import com.freelance.kiosk_backend.application.dto.testhistory.enums.TestRange;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name = "test_history")
public class TestHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity users;

    @ManyToOne
    @JoinColumn(name = "custom_package_history_id")
    private CustomPackageHistoryEntity customPackage;

    @ManyToOne
    @JoinColumn(name = "medical_package_history_id")
    private MedicalPackageHistoryEntity medicalPackage;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String result;

    @Enumerated(EnumType.STRING)
    private TestRange range;

    @Column(name = "is_general_test")
    private Boolean isGeneralTest = false;

    @Column(name = "created_at" ,nullable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;

}