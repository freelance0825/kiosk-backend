package com.freelance.kiosk_backend.application.dto.medicalpackage;

import com.freelance.kiosk_backend.application.dto.test.TestResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicalPackageRequestDto {

    private String name;

    private Long patientId;

    private List<TestResponseDto> tests;
}
