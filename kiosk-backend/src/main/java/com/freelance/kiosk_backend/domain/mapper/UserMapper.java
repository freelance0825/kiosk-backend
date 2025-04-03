package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDto toDto (UserEntity userEntity);

    List<UserResponseDto> toDtoList(List<UserEntity> userEntity);

}
