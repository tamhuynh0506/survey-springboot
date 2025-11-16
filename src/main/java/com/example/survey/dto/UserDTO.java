package com.example.survey.dto;

import com.example.survey.entity.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserDTO {
    private UUID id;
    private String name;
    private String email;
    private User.Role role;
}
