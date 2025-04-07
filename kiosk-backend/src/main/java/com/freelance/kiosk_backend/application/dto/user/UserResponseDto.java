package com.freelance.kiosk_backend.application.dto.user;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserResponseDto {

    private Long id;

    private String name;

    private String age;

    private String gender;

    private String address;

    private String dateOfBirth;

    private String phoneNumber;

    private String email;

    @Column(columnDefinition = "TEXT")
    private String imageBase64;

}
