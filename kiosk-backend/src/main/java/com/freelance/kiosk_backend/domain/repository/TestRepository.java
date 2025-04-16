package com.freelance.kiosk_backend.domain.repository;

import com.freelance.kiosk_backend.domain.entity.TestEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepository extends JpaRepository<TestEntity, Long> {

    List<TestEntity> findByUsers_Id(Long patientId);

}


