package com.freelance.kiosk_backend.application.dto.appointment;

import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentResponseDto {

    private Long id;

    private DoctorResponseDto doctor;

    private UserResponseDto patient;

    private PostConsultationResponseDto postConsultation;

    private String imageBase64;

    private String date;

    private String time;

    private String year;

    private String name;

    private String specialization;

    private String healthComplaints;
}
