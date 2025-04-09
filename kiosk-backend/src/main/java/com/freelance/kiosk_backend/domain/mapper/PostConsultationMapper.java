package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationRequestDto;
import com.freelance.kiosk_backend.application.dto.postconsultation.PostConsultationResponseDto;
import com.freelance.kiosk_backend.domain.entity.PostConsultationEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostConsultationMapper {

    PostConsultationEntity toEntity(PostConsultationResponseDto dto);

    PostConsultationEntity mapToEntity(PostConsultationRequestDto requestDto);

    PostConsultationResponseDto toDto(PostConsultationEntity postConsultationEntity);
}
