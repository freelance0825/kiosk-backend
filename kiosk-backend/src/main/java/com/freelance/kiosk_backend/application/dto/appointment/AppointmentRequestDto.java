package com.freelance.kiosk_backend.application.dto.appointment;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentRequestDto {

    private Long id;

    private Long doctorId;

    private Long patientId;

    private LocalDateTime dateTime;

    private String healthComplaints;

    private String name;

    private String specialization;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;

}
