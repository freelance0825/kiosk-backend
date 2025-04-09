package com.freelance.kiosk_backend.infrastructure.adapter.internal;

import com.freelance.kiosk_backend.domain.entity.PostConsultationEntity;
import com.freelance.kiosk_backend.domain.repository.PostConsultationRepository;
import com.freelance.kiosk_backend.infrastructure.port.PostConsultationPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
@RequiredArgsConstructor
public class PostConsultationPersistenceAdapter implements PostConsultationPersistencePort {

    private final PostConsultationRepository postConsultationRepository;

    @Override
    public PostConsultationEntity save(PostConsultationEntity postConsultation) {
        return postConsultationRepository.save(postConsultation);
    }

    @Override
    public Optional<PostConsultationEntity> findById(Long id) {
        return postConsultationRepository.findById(id);
    }

}
