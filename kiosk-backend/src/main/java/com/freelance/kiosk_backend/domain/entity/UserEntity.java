package com.freelance.kiosk_backend.domain.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String age;

    private String gender;

    private String address;

    @Column(name = "date_of_birth")
    private String dateOfBirth;

    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "image_base64",columnDefinition = "TEXT")
    private String imageBase64;

}
