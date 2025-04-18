package com.freelance.kiosk_backend.application.dto.postconsultation;

import com.freelance.kiosk_backend.application.dto.medicine.MedicineDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class PostConsultationRequestDto {

    private Long doctorId;

    private Long patientId;

    private Long appointmentId;

    private List<MedicineDto> medicines; // List of prescriptions with medicines

    private LocalDateTime dateTime;

    private String diagnosis;

    private String suggestions;

    private String followUpDate;

    private String signature; // Base64 s
}
