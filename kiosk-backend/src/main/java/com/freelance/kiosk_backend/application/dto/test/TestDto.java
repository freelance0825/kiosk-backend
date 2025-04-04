package com.freelance.kiosk_backend.application.dto.test;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TestDto {

    private Long id;

    private String name;

    @Column(columnDefinition = "TEXT")
    private String icon;
}
