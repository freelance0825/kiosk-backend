package com.freelance.kiosk_backend.application.dto.medicalpackagehistory;

import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MedicalPackageHistoryResponseDto {

    private Long id;

    private String name;

    private String patientId;

    private List<TestHistoryResponseDto> tests;

}
