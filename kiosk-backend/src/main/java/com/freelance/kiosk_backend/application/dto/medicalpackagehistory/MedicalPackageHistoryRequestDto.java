package com.freelance.kiosk_backend.application.dto.medicalpackagehistory;

import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class MedicalPackageHistoryRequestDto {

    private String name;

    private Long patientId;

    private Boolean isGeneralTest;

    private List<TestHistoryResponseDto> tests;
}
