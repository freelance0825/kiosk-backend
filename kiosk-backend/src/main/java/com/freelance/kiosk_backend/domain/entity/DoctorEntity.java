package com.freelance.kiosk_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="doctor")
public class DoctorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT")
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
