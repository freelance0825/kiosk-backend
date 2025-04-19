package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.testhistory.MedicalTestHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalTestMapper {

    MedicalTestHistoryResponseDto toDto (TestHistoryEntity testHistoryEntity);

}
