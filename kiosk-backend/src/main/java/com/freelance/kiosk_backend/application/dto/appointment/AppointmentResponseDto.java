package com.freelance.kiosk_backend.application.dto.appointment;

import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponseDto {

    private Long id;

    private DoctorResponseDto doctor;

    private UserResponseDto patient;

    private PostConsultationResponseDto postConsultation;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String year;

    private String name;

    private String specialization;

    private String healthComplaints;
}
