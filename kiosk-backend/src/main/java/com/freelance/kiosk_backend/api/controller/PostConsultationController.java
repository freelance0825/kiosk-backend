package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationRequestDto;
import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import com.freelance.kiosk_backend.application.service.PostConsultationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/postConsultations")
@RequiredArgsConstructor
@Slf4j
public class PostConsultationController {

    private final PostConsultationService postConsultationService;

    @PostMapping
    public ResponseEntity<PostConsultationResponseDto> createPostConsultation(@RequestBody PostConsultationRequestDto request) throws IOException {
        try {
            // Log input data
            log.info("Creating PostConsultation: doctorId={}, patientId={}, date={}, time={}, year={}, diagnosis={}, suggestions={}, followUpDate={}, signature={}",
                    request.getDoctorId(), request.getPatientId(), request.getDate(), request.getTime(), request.getYear(),
                    request.getDiagnosis(), request.getSuggestions(), request.getFollowUpDate(), request.getSignature());

            // Call service method
            PostConsultationResponseDto postConsultation = postConsultationService.createPostConsultation(request);
            log.info("PostConsultation created successfully: {}", postConsultation);

            return new ResponseEntity<>(postConsultation, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating PostConsultation", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


}
