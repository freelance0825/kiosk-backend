package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.doctor.DoctorRequestDto;
import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.domain.entity.DoctorEntity;
import com.freelance.kiosk_backend.domain.mapper.DoctorMapper;
import com.freelance.kiosk_backend.infrastructure.port.DoctorPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DoctorService {

    private final DoctorPersistencePort doctorPersistencePort;

    private final DoctorMapper doctorMapper;

    public DoctorResponseDto saveDoctorWithImage(DoctorRequestDto request) throws IOException {
        log.info("Processing doctor registration: {}", request.getName());

        // Convert image to Base64
        String imageBase64 = encodeImageToBase64(request.getImage());
        log.info("Converted image to Base64 for doctor: {}", request.getName());

        // Map DTO to Entity and set imageBase64
        DoctorEntity doctorEntity = doctorMapper.toEntity(request);
        doctorEntity.setImageBase64(imageBase64);

        // Save doctor entity
        DoctorEntity savedDoctor = doctorPersistencePort.save(doctorEntity);
        log.info("Successfully saved doctor: {}", savedDoctor.getName());

        return doctorMapper.toDto(savedDoctor);
    }

    public DoctorResponseDto updateDoctor(Long id, DoctorRequestDto request) throws IOException {
        log.info("Updating doctor with ID: {}", id);

        try {
            DoctorEntity existingDoctor = doctorPersistencePort.findById(id)
                    .orElseThrow(() -> new IOException("Doctor not found for ID: " + id));

            // Update fields only if present in the request
            doctorMapper.updateDoctorsFromDto(request, existingDoctor);

            // Save the updated doctor entity
            DoctorEntity updatedDoctor = doctorPersistencePort.save(existingDoctor);
            log.info("Successfully updated doctor: {}", updatedDoctor.getName());

            return doctorMapper.toDto(updatedDoctor);

        } catch (Exception e) {
            log.error("Error updating doctor: {}", e.getMessage());
            throw new IOException(e.getMessage());
        }
    }

    public List<DoctorResponseDto> getAllDoctors() {
        List<DoctorEntity> doctorList = doctorPersistencePort.findAll();
        return doctorMapper.toDtoList(doctorList);
    }

    public List<DoctorResponseDto> getLiveDoctors(String status) {
        List<DoctorEntity> doctorList = doctorPersistencePort.findAllByStatus(status).orElse(null);
        return doctorMapper.toDtoList(doctorList);
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
