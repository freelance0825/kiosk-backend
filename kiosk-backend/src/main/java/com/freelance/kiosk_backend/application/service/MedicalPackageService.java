package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.medicalpackage.MedicalPackageResponseDto;
import com.freelance.kiosk_backend.domain.entity.MedicalPackageEntity;
import com.freelance.kiosk_backend.domain.mapper.MedicalPackageMapper;
import com.freelance.kiosk_backend.infrastructure.port.MedicalPackagePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalPackageService {

    private final MedicalPackagePersistencePort medicalPackagePersistencePort;

    private final MedicalPackageMapper medicalPackageMapper;

    public MedicalPackageResponseDto createPackage(MedicalPackageRequestDto request) throws IOException {
        try {
            log.info("Creating a new medical package with name: {}", request.getName());
            log.info("Streams initial: {}", request.getTests());


            MedicalPackageEntity medicalPackage = new MedicalPackageEntity();
            medicalPackage.setName(request.getName());
            medicalPackage.setIcon(request.getImageBase64());

            String testsString = request.getTests().stream()
                    .map(test -> String.format("[{name=%s, icon='%s'}]", test.getName(), test.getIcon()))
                    .collect(Collectors.joining(", "));

            medicalPackage.setTests(testsString);

            MedicalPackageEntity savedEntity = medicalPackagePersistencePort.save(medicalPackage);
            MedicalPackageResponseDto responseDto = medicalPackageMapper.toDto(savedEntity);
            log.info("Medical package created successfully with ID: {}", responseDto.getId());
            return responseDto;
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while creating medical package", e);
        }
    }

    public List<MedicalPackageResponseDto> getAllMedicalPackages() {
        List<MedicalPackageEntity> mpList = medicalPackagePersistencePort.findAll();
        return medicalPackageMapper.toDtoList(mpList);
    }

    public MedicalPackageResponseDto updateMedicalPackage(Long id, MedicalPackageRequestDto request) throws IOException {
        log.info("Updating medical package with ID: {}", id);
        try {
            MedicalPackageEntity existingPackage = medicalPackagePersistencePort.findById(id).orElse(null);
            if (existingPackage == null) {
                throw new IOException("Medical package not found for ID: " + id);
            }

            // Update fields
            existingPackage.setName(request.getName());
            existingPackage.setIcon(request.getImageBase64());

            String testsString = request.getTests().stream()
                    .map(test -> String.format("[{name=%s, icon='%s'}]", test.getName(), test.getIcon()))
                    .collect(Collectors.joining(", "));

            existingPackage.setTests(testsString);

            MedicalPackageEntity updatedMedicalPackage = medicalPackagePersistencePort.save(existingPackage);
            MedicalPackageResponseDto responseDto = medicalPackageMapper.toDto(updatedMedicalPackage);
            log.info("Medical package updated successfully with ID: {}", updatedMedicalPackage.getId());
            return responseDto;
        } catch (Exception e) {
            log.error("Error updating medical package: {}", e.getMessage(), e);
            throw new IOException("Unexpected error occurred while updating medical package", e);
        }
    }

}

