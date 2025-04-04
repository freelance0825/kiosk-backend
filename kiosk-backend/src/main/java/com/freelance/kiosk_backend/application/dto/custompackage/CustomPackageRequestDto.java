package com.freelance.kiosk_backend.application.dto.custompackage;

import com.freelance.kiosk_backend.application.dto.test.TestDto;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomPackageRequestDto {

    private String name;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;

    private List<TestDto> tests;

    private Long patientId;
}
