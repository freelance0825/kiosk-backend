package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.PostConsultationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostConsultationRepository extends JpaRepository<PostConsultationEntity, Long> {
}
