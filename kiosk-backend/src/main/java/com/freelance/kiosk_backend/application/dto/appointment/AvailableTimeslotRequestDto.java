package com.freelance.kiosk_backend.application.dto.appointment;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class AvailableTimeslotRequestDto {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate date;

    private String zoneId = "UTC"; // default
}
