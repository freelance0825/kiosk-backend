package com.freelance.kiosk_backend.application.service;

import com.freelance.kiosk_backend.application.dto.testhistory.MedicalTestHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import com.freelance.kiosk_backend.domain.mapper.MedicalTestMapper;
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

    public List<MedicalTestHistoryResponseDto> getMedicalTestHistoryByPatientId(Long patientId) {
        List<TestHistoryEntity> testEntities = testHistoryPersistencePort.findByPatientId(patientId);

        // Group by package (custom or medical) and map to DTOs
        Map<String, List<TestHistoryEntity>> groupedByPackage = testEntities.stream()
                .collect(Collectors.groupingBy(entity -> {
                    if (entity.getCustomPackage() != null) {
                        return entity.getCustomPackage().getName().toString();  // Adjust field name if needed
                    } else if (entity.getMedicalPackage() != null) {
                        return entity.getMedicalPackage().getName().toString(); // Adjust field name if needed
                    } else {
                        return "Uncategorized";
                    }
                }));

        // Build list of MedicalTestHistoryResponseDto from grouped results
        List<MedicalTestHistoryResponseDto> response = new ArrayList<>();

        for (Map.Entry<String, List<TestHistoryEntity>> entry : groupedByPackage.entrySet()) {
            List<TestHistoryEntity> tests = entry.getValue();
            if (tests.isEmpty()) continue;

            // Assume all tests under a package belong to the same user
            TestHistoryEntity first = tests.get(0);

            MedicalTestHistoryResponseDto dto = new MedicalTestHistoryResponseDto();
            dto.setUserId(first.getUsers().getId());
            dto.setUserName(first.getUsers().getName());
            dto.setUserAge(first.getUsers().getAge());
            dto.setUserGender(first.getUsers().getGender());
            dto.setUserAddress(first.getUsers().getAddress());
            dto.setUserDob(first.getUsers().getDateOfBirth().toString()); // format as needed
            dto.setUserPhoneNumber(first.getUsers().getPhoneNumber());
            dto.setCreatedAt(first.getCreatedAt());
            dto.setPackageName(entry.getKey());

            List<TestHistoryResponseDto> testDtos = tests.stream().map(test -> {
                TestHistoryResponseDto t = new TestHistoryResponseDto();
                t.setId(test.getId());
                t.setName(test.getName());
                t.setResult(test.getResult());
                t.setRange(test.getRange().name());
                return t;
            }).toList();

            dto.setTests(testDtos);
            response.add(dto);
        }

        // Optional: sort by createdAt descending
        response.sort(Comparator.comparing(MedicalTestHistoryResponseDto::getCreatedAt).reversed());

        return response;
    }
}