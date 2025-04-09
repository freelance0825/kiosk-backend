package com.freelance.kiosk_backend.application.dto.prescription;

import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PrescriptionResponseDto {

    private Long id;

    private String name;

    private String morning;

    private String afternoon;

    private String night;

    private String notes;

    private String duration;

    private PostConsultationResponseDto postConsultation;

    private String imageBase64;
}
