package com.freelance.kiosk_backend.application.dto.appointment;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
public class AppointmentResponseDto {

    private Long id;

    private DoctorResponseDto doctor;

    private UserResponseDto patient;

    private PostConsultationForAppointmentDto postConsultation;

    private String imageBase64;

    @JsonFormat(pattern = "d MMMM yyyy, HH:mm", shape = JsonFormat.Shape.STRING)
    private OffsetDateTime dateTime;

    private String specialization;

    private String healthComplaints;
}
