package com.freelance.kiosk_backend.infrastructure.port;

import com.freelance.kiosk_backend.domain.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserPersistencePort {

    Optional<UserEntity> findByPhoneNumber(String phoneNumber);

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findById(Long id);

    List<UserEntity> findAll();
}
