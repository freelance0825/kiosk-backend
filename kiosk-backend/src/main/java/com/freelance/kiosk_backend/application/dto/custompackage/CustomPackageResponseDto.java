package com.freelance.kiosk_backend.application.dto.custompackage;

import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomPackageResponseDto {

    private Long id;

    private String name;

    private String icon;

    private String tests;

    private UserResponseDto user;
}
