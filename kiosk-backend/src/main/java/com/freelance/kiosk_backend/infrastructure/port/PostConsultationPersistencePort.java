package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.PostConsultationEntity;

import java.util.Optional;

public interface PostConsultationPersistencePort {

    PostConsultationEntity save(PostConsultationEntity postConsultation);

    Optional<PostConsultationEntity> findById(Long id);
}
