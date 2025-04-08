package com.freelance.kiosk_backend.api.controller;

import com.freelance.kiosk_backend.application.dto.user.UserLoginRequestDto;
import com.freelance.kiosk_backend.application.dto.user.UserRequestDto;
import com.freelance.kiosk_backend.application.dto.user.UserResponseDto;
import com.freelance.kiosk_backend.application.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
@Slf4j
public class LoginController {

    private final UserService userService;

    // Login endpoint that accepts phone number in the request body
    @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserResponseDto login(@RequestBody @Validated UserLoginRequestDto loginRequest) {
        log.info("Received login request with phone number: {}", loginRequest.getPhoneNumber());

        try {
            UserResponseDto userLogin = userService.loginByPhoneNumber(loginRequest.getPhoneNumber());
            log.info("Login successful for user: {}", userLogin.getPhoneNumber());
            return userLogin;
        } catch (RuntimeException e) {
            log.error("Login failed: {}", e.getMessage());
            throw e;
        }
    }

    @GetMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping(value = "users/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> addUser(@ModelAttribute UserRequestDto request) throws IOException {

        UserResponseDto savedUser = userService.saveUserWithImage(request);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping(value = "/users/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UserResponseDto> updateUsers(@PathVariable Long id,
                                                       @ModelAttribute UserRequestDto request) throws IOException {
        log.info("Received request for user with ID: {}", id);

        UserResponseDto savedUser = userService.updateUser(id,request);
        return ResponseEntity.ok(savedUser);
    }
}




