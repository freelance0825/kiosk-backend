package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.appointment.*;
import com.freelance.kiosk_backend.application.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@Slf4j
public class AppointmentController {

    private final AppointmentService appointmentService;

    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentResponseDto> createAppointment(@RequestBody AppointmentRequestDto appointmentDto) {
        AppointmentResponseDto savedAppointment = appointmentService.createAppointment(appointmentDto);
        log.info("Appointment created successfully: {}", savedAppointment);

        return ResponseEntity.ok(savedAppointment);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentResponseDto> updateAppointment(@PathVariable Long id,
                                                                    @RequestBody UpdateAppointmentRequestDto updatedAppointment) {
        AppointmentResponseDto response = appointmentService.updateAppointment(id, updatedAppointment);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        log.info("Appointment with ID: {} has been deleted successfully.", id);
        return ResponseEntity.ok("Appointment with ID: " + id + " has been deleted successfully.");
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

    // Get all appointments
    @GetMapping(value = "/list", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        List<AppointmentResponseDto> appointments = appointmentService.getAllAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping(value = "/timeslots/{doctorId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AvailableTimeslotResponseDto> getAvailableTimeSlots(@PathVariable Long doctorId,
                                                                              @ModelAttribute AvailableTimeslotRequestDto request) {

        AvailableTimeslotResponseDto response = appointmentService.getAvailableTimeSlotsForDay(doctorId, request);
        return ResponseEntity.ok(response);
    }


}
