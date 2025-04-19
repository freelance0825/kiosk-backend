package com.freelance.kiosk_backend.application.dto.testhistory;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestHistoryRequestDto {

    private String name;

    private Double result;

    private String range;


}
