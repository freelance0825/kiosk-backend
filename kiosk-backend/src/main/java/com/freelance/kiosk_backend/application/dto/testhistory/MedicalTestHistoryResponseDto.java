package com.freelance.kiosk_backend.application.dto.testhistory;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class MedicalTestHistoryResponseDto {

    private Long userId;

    private String userName;

    private String packageName;

    private String userAge;

    private String userGender;

    private String userAddress;

    private String userDob;

    private String userPhoneNumber;

    private TestHistoryResponseDto tests;

    private OffsetDateTime createdAt;

}
