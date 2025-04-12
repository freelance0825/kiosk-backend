package com.freelance.kiosk_backend.application.dto.appointment;

import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AppointmentResponseDto {

    private Long id;

    private DoctorResponseDto doctor;

    private UserResponseDto patient;

    private PostConsultationForAppointmentDto postConsultation;

    private String imageBase64;

    private LocalDateTime dateTime;

    private String specialization;

    private String healthComplaints;
}
