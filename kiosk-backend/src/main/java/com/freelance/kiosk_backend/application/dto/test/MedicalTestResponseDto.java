package com.freelance.kiosk_backend.application.dto.test;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class MedicalTestResponseDto {

    private Long userId;

    private String userName;

    private String packageName;

    private String userAge;

    private String userGender;

    private String userAddress;

    private String userDob;

    private String userPhoneNumber;

    private TestResponseDto tests;

    private OffsetDateTime createdAt;

}
