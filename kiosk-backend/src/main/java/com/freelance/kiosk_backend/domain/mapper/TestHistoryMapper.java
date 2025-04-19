package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.testhistory.TestHistoryResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestHistoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestHistoryMapper {

    List<TestHistoryResponseDto> toDtoList (List<TestHistoryEntity> testEntities);

}
