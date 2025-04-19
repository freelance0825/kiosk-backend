package com.freelance.kiosk_backend.application.dto.testhistory;

import com.fasterxml.jackson.annotation.JsonFormat;
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

    @JsonFormat(pattern = "d MMMM yyyy, HH:mm", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime createdAt;

}
