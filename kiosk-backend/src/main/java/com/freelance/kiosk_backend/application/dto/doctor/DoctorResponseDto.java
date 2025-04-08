package com.freelance.kiosk_backend.application.dto.doctor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponseDto {

    private Long id;

    private String imageBase64;

    private String name;

    private String specialization;

    private String status;

    private double review;

    private String email;

    private String password;

    private String state;

    private String clinic;

    private String gender;

    private String age;

    private String dob;
    
}
