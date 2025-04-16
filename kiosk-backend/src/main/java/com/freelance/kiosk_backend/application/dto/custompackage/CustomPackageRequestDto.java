package com.freelance.kiosk_backend.application.dto.custompackage;

import com.freelance.kiosk_backend.application.dto.test.TestRequestDto;
import com.freelance.kiosk_backend.application.dto.test.TestResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomPackageRequestDto {

    private String name;

    private Long patientId;

    private List<TestRequestDto> tests;

}
