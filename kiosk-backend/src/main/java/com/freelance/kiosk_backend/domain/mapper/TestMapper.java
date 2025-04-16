package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.test.TestResponseDto;
import com.freelance.kiosk_backend.domain.entity.TestEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TestMapper {

    List<TestResponseDto> toDtoList (List<TestEntity> testEntities);

}
