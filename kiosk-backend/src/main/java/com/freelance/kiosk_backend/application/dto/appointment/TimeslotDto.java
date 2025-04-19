package com.freelance.kiosk_backend.application.dto.appointment;

import lombok.Data;

@Data
public class TimeslotDto {

    private String time;
    private boolean isAvailable;
}
