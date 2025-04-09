package com.freelance.kiosk_backend.application.dto.postconsultation;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PostConsultationResponseDto {

    private Long id;

    //private DoctorResponseDto doctor;

    //private UserResponseDto patient;

    //private AppointmentResponseDto appointment;

    //private List<MedicineDto> medicines; // List of prescriptions with medicines

    private String date;

    private String time;

    private String year;

    private String diagnosis;

    private String suggestions;

    private String followUpDate;

    private String signature; // Base64 signature image

}
