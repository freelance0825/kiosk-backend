package com.freelance.kiosk_backend.application.dto.doctor;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class DoctorRequestDto {

    private String name;

    private Double review;

    private String specialization;

    private String state;

    private String status;

    private String clinic;

    private String gender;

    private String age;

    private String dob;

    private String email;

    private String password;

    private MultipartFile image;

}
