package com.example.survey.dto;

import com.example.survey.enums.UserRole;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private UserRole role;
}
