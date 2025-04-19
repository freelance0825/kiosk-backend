package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.custompackagehistory.CustomPackageHistoryRequestDto;
import com.freelance.kiosk_backend.application.dto.custompackagehistory.CustomPackageHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.medicalpackagehistory.MedicalPackageHistoryRequestDto;
import com.freelance.kiosk_backend.application.dto.medicalpackagehistory.MedicalPackageHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.testhistory.MedicalTestHistoryResponseDto;
import com.freelance.kiosk_backend.application.service.CustomPackageHistoryService;
import com.freelance.kiosk_backend.application.service.MedicalPackageHistoryService;
import com.freelance.kiosk_backend.application.service.MedicalTestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class MedicalPackageHistoryController {

    private final MedicalPackageHistoryService medicalPackageHistoryService;

    private final CustomPackageHistoryService customPackageHistoryService;

    private final MedicalTestService medicalTestService;

    @PostMapping(value = "/medical/package/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MedicalPackageHistoryResponseDto> createMedicalPackageHistory(@RequestBody MedicalPackageHistoryRequestDto request) {
        MedicalPackageHistoryResponseDto medicalPackage = medicalPackageHistoryService.createMedicalPackageHistory(request);
        log.info("MedicalPackage created successfully: {}", medicalPackage);
        return ResponseEntity.ok(medicalPackage);
    }

    @PostMapping(value = "/custom/package/history", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomPackageHistoryResponseDto> createCustomPackageHistory(@RequestBody CustomPackageHistoryRequestDto request) {
        CustomPackageHistoryResponseDto customPackage = customPackageHistoryService.createCustomPackageHistory(request);
        log.info("CustomPackage created successfully: {}", customPackage);
        return ResponseEntity.ok(customPackage);
    }

    @GetMapping(value = "/{patientId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<MedicalTestHistoryResponseDto>> getMedicalTestHistoryByPatientId(@PathVariable Long patientId) {
        List<MedicalTestHistoryResponseDto> dtoList = medicalTestService.getMedicalTestHistoryByPatientId(patientId);
        log.info("Fetched medical tests for patientId {}: {}", patientId, dtoList);
        return ResponseEntity.ok(dtoList);
    }

}
