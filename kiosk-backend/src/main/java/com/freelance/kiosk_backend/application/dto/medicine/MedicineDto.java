package com.freelance.kiosk_backend.application.dto.medicine;

import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicineDto {

    private String name;

    private String morning;

    private String afternoon;

    private String night;

    private String notes;

    private String duration;

    // NEW: Only keep ID reference to avoid circular dependency
    private Long postConsultationId;

    // private PostConsultationResponseDto postConsultation;

    private String imageBase64;
}
