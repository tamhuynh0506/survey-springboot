package com.example.survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

import com.example.survey.enums.UserRole;

@Entity
@Table(name = "app_user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    private boolean emailVerified = false;

    private String verificationToken;

    private Date verificationTokenExpiry;

    // reset password in 1 device for now
    private String resetPasswordToken;

    private Date resetPasswordExpiry;

    @Enumerated(EnumType.STRING)
    private UserRole role;
}