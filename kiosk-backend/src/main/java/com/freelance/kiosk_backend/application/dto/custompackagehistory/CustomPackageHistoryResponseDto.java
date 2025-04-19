package com.freelance.kiosk_backend.application.dto.custompackagehistory;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
public class CustomPackageHistoryResponseDto {

    private Long userId;

    private String userName;

    private String packageName;

    private String userAge;

    private String userGender;

    private String userAddress;

    private String userDob;

    private String userPhoneNumber;

    private List<TestHistoryResponseDto> tests;

    @JsonFormat(pattern = "d MMMM yyyy, HH:mm", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdAt;

}
