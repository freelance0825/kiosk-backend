package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.test.MedicalTestResponseDto;
import com.freelance.kiosk_backend.application.dto.test.TestResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestEntity;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
@DecoratedWith(MedicalTestMapperDecorator.class)
public interface MedicalTestMapper {

    MedicalTestResponseDto toDto (TestEntity testEntity);

    TestResponseDto toTestDto(TestEntity testEntity);

}
