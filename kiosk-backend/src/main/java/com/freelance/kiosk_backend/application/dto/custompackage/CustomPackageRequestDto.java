package com.freelance.kiosk_backend.application.dto.custompackage;

import com.freelance.kiosk_backend.application.dto.medicalpackage.TestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomPackageRequestDto {

    private String name;

    private String imageBase64;

    private List<TestDto> tests; // List of tests
}
