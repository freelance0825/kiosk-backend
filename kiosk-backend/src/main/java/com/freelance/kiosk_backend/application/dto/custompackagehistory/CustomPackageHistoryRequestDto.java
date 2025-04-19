package com.freelance.kiosk_backend.application.dto.custompackagehistory;

import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryRequestDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomPackageHistoryRequestDto {

    private String name;

    private Long patientId;

    private List<TestHistoryRequestDto> tests;

}
