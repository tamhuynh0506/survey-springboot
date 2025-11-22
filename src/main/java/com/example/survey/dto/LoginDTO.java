package com.example.survey.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    String email;

    @NotBlank(message = "Password cannot be empty")
    String password;
}
