package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.testhistory.MedicalTestHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import com.freelance.kiosk_backend.domain.mapper.MedicalTestMapper;
import com.freelance.kiosk_backend.domain.mapper.TestHistoryMapper;
import com.freelance.kiosk_backend.infrastructure.port.TestHistoryPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MedicalTestService {

    private final TestHistoryPersistencePort testHistoryPersistencePort;
    private final MedicalTestMapper medicalTestMapper;
    private final TestHistoryMapper testHistoryMapper;

    public List<MedicalTestHistoryResponseDto> getMedicalTestHistoryByPatientId(Long patientId) {

        // Fetch all test entries for a given patient
        List<TestHistoryEntity> testEntities = testHistoryPersistencePort.findByPatientId(patientId);

        // Group entries by either custom or medical package history ID
        Map<String, List<TestHistoryEntity>> grouped = testEntities.stream()
                .collect(Collectors.groupingBy(entity -> {
                    if (entity.getCustomPackage() != null) {
                        return "CUSTOM_" + entity.getCustomPackage().getId();
                    } else if (entity.getMedicalPackage() != null) {
                        return "MEDICAL_" + entity.getMedicalPackage().getId();
                    } else {
                        return "UNKNOWN_" + entity.getId(); // fallback (shouldn't usually happen)
                    }
                }));

        List<MedicalTestHistoryResponseDto> responseList = new ArrayList<>();

        for (List<TestHistoryEntity> group : grouped.values()) {
            if (group.isEmpty()) continue;

            TestHistoryEntity first = group.get(0);

            // Set base info from first entity
            MedicalTestHistoryResponseDto dto = new MedicalTestHistoryResponseDto();
            dto.setUserId(first.getUsers().getId());
            dto.setUserName(first.getUsers().getName());
            dto.setUserAge(first.getUsers().getAge());
            dto.setUserGender(first.getUsers().getGender());
            dto.setUserAddress(first.getUsers().getAddress());
            dto.setUserDob(first.getUsers().getDateOfBirth().toString());
            dto.setUserPhoneNumber(first.getUsers().getPhoneNumber());
            dto.setCreatedAt(first.getCreatedAt());

            if (first.getCustomPackage() != null) {
                dto.setPackageName(String.valueOf(first.getCustomPackage().getName()));
            } else if (first.getMedicalPackage() != null) {
                dto.setPackageName(String.valueOf(first.getMedicalPackage().getName()));
            }

            // Map all tests in this transaction
            dto.setTests(group.stream()
                    .map(testHistoryMapper::toDto)
                    .collect(Collectors.toList()));

            responseList.add(dto);
        }

        // Sort entries by creation date (latest first)
        responseList.sort(Comparator.comparing(MedicalTestHistoryResponseDto::getCreatedAt).reversed());

        return responseList;
    }
}
