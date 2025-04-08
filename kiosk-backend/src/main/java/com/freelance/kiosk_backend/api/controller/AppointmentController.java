package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentRequestDto;
import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import com.freelance.kiosk_backend.application.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentResponseDto> createAppointment(@RequestBody AppointmentRequestDto appointmentDto,
                                                                    @RequestParam Long currentPatient,
                                                                    @RequestParam Long chosenDoctor) {

        try {
            AppointmentResponseDto savedAppointment = appointmentService.saveAppointmentWithImage(appointmentDto,
                    currentPatient, chosenDoctor);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedAppointment);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Long id, @RequestBody AppointmentRequestDto appointmentDto) {
        try {
            AppointmentResponseDto updatedAppointment = appointmentService.updateAppointment(id, appointmentDto);
            return ResponseEntity.ok(updatedAppointment);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentResponseDto> getAppointmentById(@PathVariable Long id) {
        AppointmentResponseDto appointment = appointmentService.getAppointmentById(id);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/patient/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByPatientId(@PathVariable Long id) {
        List<AppointmentResponseDto> appointment = appointmentService.getAppointmentsByPatientId(id);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping(value = "/doctor/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentResponseDto>> getAppointmentsByDoctorId(@PathVariable Long id) {
        List<AppointmentResponseDto> appointment = appointmentService.getAppointmentsByDoctorId(id);
        if (appointment != null) {
            return ResponseEntity.ok(appointment);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        try {
            appointmentService.deleteAppointment(id);
            return ResponseEntity.ok("Appointment with ID: " + id + " has been deleted successfully.");
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error deleting appointment: " + e.getMessage());
        }
    }

    // Get all appointments
    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

}
