package com.freelance.kiosk_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="custom_package")
public class CustomPackageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String icon;

    @Column(columnDefinition = "TEXT")
    private String tests;

    @OneToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "consult_id", nullable = false)
    private UserEntity user;
}
