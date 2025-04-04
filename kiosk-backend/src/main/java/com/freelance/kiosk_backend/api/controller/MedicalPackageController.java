package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageResponseDto;
import com.freelance.kiosk_backend.application.service.CustomPackageService;
import com.freelance.kiosk_backend.application.service.MedicalPackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/package")
@RequiredArgsConstructor
@Slf4j
public class MedicalPackageController {

    private final MedicalPackageService medicalPackageService;

    private final CustomPackageService customPackageService;

    @PostMapping
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

    @PostMapping("/custom")
    public ResponseEntity<CustomPackageResponseDto> createCustomPackage(@RequestBody CustomPackageRequestDto request) throws IOException {
        try {
            CustomPackageResponseDto customPackage = customPackageService.createPackage(request);
            log.info("CustomPackage created successfully: {}", customPackage);
            return new ResponseEntity<>(customPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating MedicalPackage", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<MedicalPackageResponseDto> getAllMedicalPackages() {
        return medicalPackageService.getAllMedicalPackages();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalPackageResponseDto> updateMedicalPackage(@PathVariable Long id,
                                                                          @RequestBody MedicalPackageRequestDto request) {
        try {
            MedicalPackageResponseDto updatedPackage = medicalPackageService.updateMedicalPackage(id, request);
            return ResponseEntity.ok(updatedPackage);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
