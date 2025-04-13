package com.freelance.kiosk_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "notification")
public class NotificationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "is_booked")
    private Boolean isBooked = false;

    @Column(name = "is_rescheduled")
    private Boolean isRescheduled = false;

    @Column(name = "is_cancelled")
    private Boolean isCancelled = false;

    @Column(name = "created_at")
    @UpdateTimestamp
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "appointment_id", nullable = false)
    private AppointmentEntity appointment;
}
