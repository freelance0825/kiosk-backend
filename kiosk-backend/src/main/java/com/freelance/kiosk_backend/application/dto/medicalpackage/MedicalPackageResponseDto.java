package com.freelance.kiosk_backend.application.dto.medicalpackage;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalPackageResponseDto {

    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String icon;

    @Column(columnDefinition = "TEXT")
    private String tests;
}
