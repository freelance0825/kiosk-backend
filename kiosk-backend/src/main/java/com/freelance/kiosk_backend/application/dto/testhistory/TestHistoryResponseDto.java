package com.freelance.kiosk_backend.application.dto.testhistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestHistoryResponseDto {

    private Long id;

    private String name;

    private String result;

    private String range;
}
