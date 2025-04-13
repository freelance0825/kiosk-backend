package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.user.UserRequestDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import com.freelance.kiosk_backend.domain.entity.NotificationEntity;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.UserMapper;
import com.freelance.kiosk_backend.infrastructure.port.NotificationPersistencePort;
import com.freelance.kiosk_backend.infrastructure.port.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserPersistencePort userPersistencePort;

    private final NotificationPersistencePort notificationPersistencePort;

    private final UserMapper userMapper;

    // Method to handle login by phone number
    public UserResponseDto loginByPhoneNumber(String phoneNumber) {
        log.info("Attempting to find user by phone number: {}", phoneNumber);

        Optional<UserEntity> userOptional = userPersistencePort.findByPhoneNumber(phoneNumber);

        if (userOptional.isPresent()) {
            UserEntity user = userOptional.get();
            log.info("User found: {}", user.getPhoneNumber());

            return userMapper.toDto(user);
        } else {
            String errorMessage = "User not found with phone number: " + phoneNumber;
            log.warn(errorMessage);
            throw new RuntimeException(errorMessage);
        }
    }

    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> usersList = userPersistencePort.findAll();
        return userMapper.toDtoList(usersList);
    }

    public UserResponseDto saveUserWithImage(UserRequestDto request) throws IOException {
        log.info("Processing user registration: {}", request.getName());

        // Convert image to Base64 only if it's not null
        String imageBase64 = encodeImageToBase64(request.getImage());
        log.info("Converted image to Base64 for user: {}", request.getName());

        // Map DTO to Entity and set imageBase64
        UserEntity userEntity = userMapper.toEntity(request);
        userEntity.setImageBase64(imageBase64);

        // Save user entity
        UserEntity savedUser = userPersistencePort.save(userEntity);
        log.info("Successfully saved user: {}", savedUser.getName());

        return userMapper.toDto(savedUser);
    }

    public UserResponseDto updateUser(Long id, UserRequestDto request) throws IOException {
        log.info("Updating user with ID: {}", id);

        try {
            // Find the existing user
            UserEntity existingUser = userPersistencePort.findById(id).orElse(null);
            if (existingUser == null) {
                throw new IOException("User not found for ID: " + id);
            }

            // Update fields only if present in the request
            userMapper.updateUsersFromDto(request, existingUser);

            // Convert image to Base64 if it's provided
            String imageBase64 = encodeImageToBase64(request.getImage());
            if (imageBase64 != null) {
                existingUser.setImageBase64(imageBase64);
            }

            // Save the updated user first
            UserEntity savedUser = userPersistencePort.save(existingUser);

            // Update notification(s) only if they're linked to an appointment
            List<NotificationEntity> userNotifications = notificationPersistencePort.findByUserId(id);
            for (NotificationEntity notification : userNotifications) {
                if (notification.getAppointment() != null) {
                    notification.setApptUserName(savedUser.getName());
                    notificationPersistencePort.save(notification);
                    log.info("Updated notification ID {} with new user name: {}", notification.getId(), savedUser.getName());
                } else {
                    log.info("Skipping notification ID {} - not linked to an appointment", notification.getId());
                }
            }

            return userMapper.toDto(savedUser);

        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    // Helper method to encode an image file to Base64
    public String encodeImageToBase64(MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            byte[] imageBytes = imageFile.getBytes();
            return Base64.getEncoder().encodeToString(imageBytes);  // Convert to Base64
        }
        return null;  // Return null if the file is not provided
    }

}
