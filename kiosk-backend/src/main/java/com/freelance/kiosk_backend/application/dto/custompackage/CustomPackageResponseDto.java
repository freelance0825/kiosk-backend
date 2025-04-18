package com.freelance.kiosk_backend.application.dto.custompackage;

import com.freelance.kiosk_backend.application.dto.test.TestResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomPackageResponseDto {

    private Long id;

    private String name;

    private String patientId;

    private List<TestResponseDto> tests;

}
