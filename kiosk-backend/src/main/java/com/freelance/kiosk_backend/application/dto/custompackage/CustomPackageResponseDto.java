package com.freelance.kiosk_backend.application.dto.custompackage;

import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomPackageResponseDto {

    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String icon;

    @Column(columnDefinition = "TEXT")
    private String tests;

    private UserResponseDto user;
}
