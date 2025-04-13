package com.freelance.kiosk_backend.application.dto.notification;

import com.freelance.kiosk_backend.application.dto.appointment.AppointmentResponseDto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class NotificationResponseDto {

    private Long id;

    private Boolean isBooked;

    private Boolean isRescheduled;

    private Boolean isCancelled;

    private LocalDateTime createAt;

    AppointmentResponseDto appointment;
}
