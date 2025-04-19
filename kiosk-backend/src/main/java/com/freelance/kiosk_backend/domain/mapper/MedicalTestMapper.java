package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.testhistory.MedicalTestHistoryResponseDto;
import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(MedicalTestMapperDecorator.class)
public interface MedicalTestMapper {

    MedicalTestHistoryResponseDto toDto (TestHistoryEntity testHistoryEntity);

    TestHistoryResponseDto toTestDto(TestHistoryEntity testHistoryEntity);

}
