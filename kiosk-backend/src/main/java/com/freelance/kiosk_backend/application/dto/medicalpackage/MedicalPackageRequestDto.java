package com.freelance.kiosk_backend.application.dto.medicalpackage;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicalPackageRequestDto {

    private String name;

    private String imageBase64;

    private List<TestDto> tests; // List of tests
}
