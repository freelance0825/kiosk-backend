package com.freelance.kiosk_backend.application.dto.appointment;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AppointmentRequestDto {

    private Long doctorId;

    private Long patientId;

    private OffsetDateTime dateTime;

    private String healthComplaints;
}
