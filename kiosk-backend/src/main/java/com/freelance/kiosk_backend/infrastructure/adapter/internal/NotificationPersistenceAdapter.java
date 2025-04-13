package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import com.freelance.kiosk_backend.domain.repository.NotificationRepository;
import com.freelance.kiosk_backend.infrastructure.port.NotificationPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class NotificationPersistenceAdapter implements NotificationPersistencePort {

    private final NotificationRepository notificationRepository;

    @Override
    public NotificationEntity save(NotificationEntity notification) {
        return notificationRepository.save(notification);
    }

    @Override
    public Optional<NotificationEntity> findByAppointmentId(Long appointmentId) {
        return notificationRepository.findByAppointmentId(appointmentId);
    }

    @Override
    public List<NotificationEntity> findByUserId(Long userId) {
        return notificationRepository.findByUsers_Id(userId);
    }
}
