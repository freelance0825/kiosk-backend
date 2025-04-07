package com.freelance.kiosk_backend.application.dto.postconsultation;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostConsultationResponseDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private DoctorResponseDto doctor;

    private UserResponseDto patient;

    private AppointmentResponseDto appointment;

    private List<MedicineDto> medicines; // List of prescriptions with medicines

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private String time;

    @Column(nullable = false)
    private String year;

    private String diagnosis;

    private String suggestions;

    private String followUpDate;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String signature; // Base64 signature image

}
