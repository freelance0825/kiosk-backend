package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.doctor.DoctorRequestDto;
import com.freelance.kiosk_backend.application.dto.doctor.DoctorResponseDto;
import com.freelance.kiosk_backend.domain.entity.DoctorEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DoctorMapper {

    @Mapping(target = "imageBase64", ignore = true) // Ignore image encoding in the mapping
    DoctorEntity toEntity(DoctorRequestDto dto);

    DoctorResponseDto toDto(DoctorEntity entity);

    List<DoctorResponseDto> toDtoList(List<DoctorEntity> doctorEntities);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateDoctorsFromDto(DoctorRequestDto dto, @MappingTarget DoctorEntity entity);

}
