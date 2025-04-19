package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageRequestDto;
import com.freelance.kiosk_backend.application.dto.custompackage.CustomPackageResponseDto;
import com.freelance.kiosk_backend.domain.entity.CustomPackageEntity;
import com.freelance.kiosk_backend.domain.mapper.CustomPackageMapper;
import com.freelance.kiosk_backend.infrastructure.port.CustomPackagePersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomPackageService {

    private final CustomPackagePersistencePort customPackagePersistencePort;

    private final CustomPackageMapper customPackageMapper;

    public CustomPackageResponseDto createCustomPackage(CustomPackageRequestDto request) throws IOException {
        try {
            log.info("Creating a new custom package with name: {}", request.getName());
            log.info("Streams initial: {}", request.getTests());

            CustomPackageEntity customPackage = new CustomPackageEntity();
            customPackage.setName(request.getName());
            customPackage.setIcon(request.getImageBase64());

            String testsString = request.getTests().stream()
                    .map(test -> String.format("[{name=%s, icon='%s'}]", test.getName(), test.getIcon()))
                    .collect(Collectors.joining(", "));

            customPackage.setTests(testsString);

            CustomPackageEntity savedEntity = customPackagePersistencePort.save(customPackage);
            CustomPackageResponseDto responseDto = customPackageMapper.toDto(savedEntity);
            log.info("Custom package created successfully with ID: {}", responseDto.getId());
            return responseDto;
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            throw new RuntimeException("Unexpected error occurred while creating medical package", e);
        }
    }

    public List<CustomPackageResponseDto> getAllCustomPackages() {
        List<CustomPackageEntity> customPackageList = customPackagePersistencePort.findAll();
        return customPackageMapper.toDtoList(customPackageList);
    }

}
