package com.freelance.kiosk_backend.application.dto.medicalpackage;

import com.freelance.kiosk_backend.application.dto.test.TestDto;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicalPackageRequestDto {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;

    private List<TestDto> tests; // List of tests
}
