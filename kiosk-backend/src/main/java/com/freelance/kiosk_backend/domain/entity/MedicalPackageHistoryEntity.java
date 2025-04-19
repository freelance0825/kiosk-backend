package com.freelance.kiosk_backend.domain.entity;

import com.freelance.kiosk_backend.application.dto.testhistory.enums.TestMedicalName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@Table(name="medical_package_history")
public class MedicalPackageHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @Enumerated(EnumType.STRING)
    private TestMedicalName name;

    @Column(name = "is_general_test")
    private Boolean isGeneralTest = false;

    @Column(name = "created_at" ,nullable = false)
    @CreationTimestamp
    private OffsetDateTime createdAt;
}
