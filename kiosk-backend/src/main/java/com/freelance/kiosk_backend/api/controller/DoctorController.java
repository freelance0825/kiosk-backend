package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.doctor.DoctorRequestDto;
import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.application.service.DoctorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/doctors")
@RequiredArgsConstructor
@Slf4j
public class DoctorController {

    private final DoctorService doctorService;

    @PostMapping("/add")
    public ResponseEntity<DoctorResponseDto> addDoctor(@ModelAttribute DoctorRequestDto request) throws IOException {
        log.info("Received doctor request with email: {}", request.getEmail());

        DoctorResponseDto response = doctorService.saveDoctorWithImage(request);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDto> updateDoctor(@PathVariable Long id, @ModelAttribute DoctorRequestDto request) {
        try {
            DoctorResponseDto updatedDoctor = doctorService.updateDoctor(id, request);
            return ResponseEntity.ok(updatedDoctor);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/list")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctors() {
        List<DoctorResponseDto> doctors = doctorService.getAllDoctors();
        return ResponseEntity.ok(doctors);
    }

    @GetMapping("/live")
    public ResponseEntity<List<DoctorResponseDto>> getAllDoctorsLive() {
        List<DoctorResponseDto> doctors = doctorService.getLiveDoctors("live");
        return ResponseEntity.ok(doctors);
    }
}
