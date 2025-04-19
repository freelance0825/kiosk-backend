package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageResponseDto;
import com.freelance.kiosk_backend.application.service.MedicalPackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/medical/package")
@RequiredArgsConstructor
@Slf4j
public class MedicalPackageController {

    private final MedicalPackageService medicalPackageService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalPackageResponseDto> createMedicalPackage(@RequestBody MedicalPackageRequestDto request) throws IOException {
        try {
            MedicalPackageResponseDto medicalPackage = medicalPackageService.createPackage(request);
            log.info("MedicalPackage created successfully: {}", medicalPackage);
            return new ResponseEntity<>(medicalPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating MedicalPackage", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalPackageResponseDto> getAllMedicalPackages() {
        return medicalPackageService.getAllMedicalPackages();
    }
}
