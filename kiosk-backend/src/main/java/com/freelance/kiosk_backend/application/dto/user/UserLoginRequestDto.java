package com.freelance.kiosk_backend.application.dto.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserLoginRequestDto {

    private String phoneNumber;

    private String email;

    private String password;
}
