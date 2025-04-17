package com.freelance.kiosk_backend.application.dto.appointment;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class UpdateAppointmentRequestDto {

    private OffsetDateTime dateTime;

}
