package com.freelance.kiosk_backend.application.dto.medicalpackage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicalPackageResponseDto {

    private Long id;

    private String name;

    private String icon;

    private String tests;
}
