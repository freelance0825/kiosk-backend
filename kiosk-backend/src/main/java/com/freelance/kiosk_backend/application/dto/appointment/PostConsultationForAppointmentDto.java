package com.freelance.kiosk_backend.application.dto.appointment;

import com.freelance.kiosk_backend.application.dto.medicine.AppointmentDto;
import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostConsultationForAppointmentDto {

    private Long id;

    private List<MedicineDto> medicines; // List of prescriptions with medicines

    private LocalDateTime dateTime;

    private String diagnosis;

    private String suggestions;

    private String followUpDate;

    private String signature; // Base64 signature image

}
