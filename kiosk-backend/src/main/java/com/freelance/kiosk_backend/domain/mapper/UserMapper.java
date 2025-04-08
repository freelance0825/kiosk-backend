package com.freelance.kiosk_backend.domain.mapper;

import com.freelance.kiosk_backend.application.dto.user.UserRequestDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import com.freelance.kiosk_backend.domain.entity.UserEntity;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "imageBase64", ignore = true) // Ignore image encoding in the mapping
    UserEntity toEntity(UserRequestDto dto);

    UserResponseDto toDto (UserEntity userEntity);

    List<UserResponseDto> toDtoList(List<UserEntity> userEntity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUsersFromDto(UserRequestDto dto, @MappingTarget UserEntity entity);

}
