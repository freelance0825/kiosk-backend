package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageResponseDto;
import com.freelance.kiosk_backend.application.dto.test.MedicalTestResponseDto;
import com.freelance.kiosk_backend.application.service.CustomPackageService;
import com.freelance.kiosk_backend.application.service.MedicalPackageService;
import com.freelance.kiosk_backend.application.service.MedicalTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tests/")
@RequiredArgsConstructor
@Slf4j
public class MedicalPackageController {

    private final MedicalPackageService medicalPackageService;

    private final CustomPackageService customPackageService;

    private final MedicalTestService medicalTestService;

    @PostMapping(value = "/medical/package", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalPackageResponseDto> createMedicalPackage(@RequestBody MedicalPackageRequestDto request) {
        MedicalPackageResponseDto medicalPackage = medicalPackageService.createMedicalPackage(request);
        log.info("MedicalPackage created successfully: {}", medicalPackage);
        return ResponseEntity.ok(medicalPackage);
    }

    @PostMapping(value = "/custom/package", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomPackageResponseDto> createCustomPackage(@RequestBody CustomPackageRequestDto request) {
        CustomPackageResponseDto customPackage = customPackageService.createCustomPackage(request);
        log.info("CustomPackage created successfully: {}", customPackage);
        return ResponseEntity.ok(customPackage);
    }

    @GetMapping(value = "/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicalTestResponseDto>> getMedicalTestByPatientId(@PathVariable Long patientId) {
        List<MedicalTestResponseDto> dtoList = medicalTestService.getMedicalTestByPatientId(patientId);
        log.info("Fetched medical tests for patientId {}: {}", patientId, dtoList);
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping(value = "medical/package",produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MedicalPackageResponseDto> getAllMedicalPackages() {
        log.info("Fetched all medical packages.");
        return medicalPackageService.getAllMedicalPackages();
    }

}
