package com.freelance.kiosk_backend.application.dto.notification;

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

    private Long appointmentId;

    private Long apptDoctorId;

    private Long apptUserId;

    private String apptUserName;

    private String apptDoctorName;

    private String apptDoctorSpecialization;

    private LocalDateTime apptDateTime;

    private LocalDateTime createdAt;

}
