package com.freelance.kiosk_backend.application.dto.medicine;

import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import jakarta.persistence.Column;
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

    private PostConsultationResponseDto postConsultation;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;
}
