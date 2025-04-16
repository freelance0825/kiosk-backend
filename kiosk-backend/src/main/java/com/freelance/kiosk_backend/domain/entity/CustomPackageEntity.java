package com.freelance.kiosk_backend.domain.entity;

import com.freelance.kiosk_backend.application.dto.test.enums.TestCustomName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="custom_package")
public class CustomPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private UserEntity patient;

    @Enumerated(EnumType.STRING)
    private TestCustomName name;

    @Column(name = "created_at" ,nullable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

}
