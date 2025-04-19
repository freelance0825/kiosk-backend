package com.freelance.kiosk_backend.application.dto.appointment;

import lombok.Data;

import java.util.List;

@Data
public class AvailableTimeslotResponseDto {

    private String date;

   // private List<String> availableTimeSlots;

    private List<TimeslotDto> availableTimeSlots;

}
