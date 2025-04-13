package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.notification.NotificationResponseDto;
import com.freelance.kiosk_backend.application.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@Slf4j
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping(value = "/patient/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<NotificationResponseDto>> getNotificationsByAppointmentPatientId(@PathVariable Long id) {
        List<NotificationResponseDto> notification = notificationService.getNotificationsByAppointmentPatientId(id);
        if (notification != null) {
            return ResponseEntity.ok(notification);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
