package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.user.UserRequestDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import com.freelance.kiosk_backend.domain.mapper.UserMapper;
import com.freelance.kiosk_backend.infrastructure.port.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserPersistencePort userPersistencePort;

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
        // Convert image to Base64 only if it's not null
        String imageBase64 = (request.getImage() != null && !request.getImage().isEmpty())
                ? encodeImageToBase64(request.getImage()) : null;  // Set to null if no image is provided

        // Create and save user entity
        UserEntity user = new UserEntity();
        user.setName(request.getName());
        user.setAddress(request.getAddress());
        user.setGender(request.getGender());
        user.setAge(request.getAge());
        user.setDateOfBirth(request.getDob());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setImageBase64(imageBase64);

        // Save user to database
        UserEntity savedUser = userPersistencePort.save(user);

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

            // Update user fields based on the request
            updateUserFields(existingUser, request);

            // Convert image to Base64 if it's provided
            String imageBase64 = (request.getImage() != null && !request.getImage().isEmpty())
                    ? encodeImageToBase64(request.getImage()) : null;
            if (imageBase64 != null) {
                existingUser.setImageBase64(imageBase64);
            }

            // Save the updated user and return the response DTO
            return userMapper.toDto(userPersistencePort.save(existingUser));

        } catch (Exception e) {
            log.error("Error updating user: {}", e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    // Helper method to encode an image file to Base64
    private String encodeImageToBase64(MultipartFile imageFile) throws IOException {
        byte[] imageBytes = imageFile.getBytes();
        return Base64.encodeBase64String(imageBytes);  // Return Base64 string
    }

    // Helper method to update user fields
    private void updateUserFields(UserEntity existingUser, UserRequestDto request) {
        if (request.getName() != null && !request.getName().isEmpty()) {
            existingUser.setName(request.getName());
        }
        if (request.getAddress() != null && !request.getAddress().isEmpty()) {
            existingUser.setAddress(request.getAddress());
        }
        if (request.getGender() != null && !request.getGender().isEmpty()) {
            existingUser.setGender(request.getGender());
        }
        if (request.getAge() != null && !request.getAge().isEmpty()) {
            existingUser.setAge(request.getAge());
        }
        if (request.getDob() != null && !request.getDob().isEmpty()) {
            existingUser.setDateOfBirth(request.getDob());
        }
        if (request.getPhoneNumber() != null && !request.getPhoneNumber().isEmpty()) {
            existingUser.setPhoneNumber(request.getPhoneNumber());
        }
    }
}
