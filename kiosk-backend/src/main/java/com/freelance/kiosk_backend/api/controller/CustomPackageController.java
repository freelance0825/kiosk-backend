package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.application.service.CustomPackageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/custom/package")
@RequiredArgsConstructor
@Slf4j
public class CustomPackageController {

    private final CustomPackageService customPackageService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomPackageResponseDto> createCustomPackage(@RequestBody CustomPackageRequestDto request) throws IOException {
        try {
            CustomPackageResponseDto customPackage = customPackageService.createCustomPackage(request);
            log.info("MedicalPackage created successfully: {}", customPackage);
            return new ResponseEntity<>(customPackage, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating MedicalPackage", e);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CustomPackageResponseDto> getAllCustomPackages() {
        return customPackageService.getAllCustomPackages();
    }
}
