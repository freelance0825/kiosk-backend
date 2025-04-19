package com.freelance.kiosk_backend.application.dto.custompackagehistory;

import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomPackageHistoryResponseDto {

    private Long id;

    private String name;

    private String patientId;

    private Boolean isGeneralTest;

    private List<TestHistoryResponseDto> tests;

}
