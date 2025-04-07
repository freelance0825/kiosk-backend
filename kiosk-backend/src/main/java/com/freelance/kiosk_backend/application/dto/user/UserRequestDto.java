package com.freelance.kiosk_backend.application.dto.user;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class UserRequestDto {

    private String name;

    private String address;

    private String gender;

    private String age;

    private String dob;

    private String phoneNumber;

    private String email;

    private MultipartFile image;
}
