package com.example.survey.service;

import com.example.survey.dto.LoginDTO;
import com.example.survey.dto.RegisterDTO;
import com.example.survey.entity.User;
import com.example.survey.exception.ApiException;
import com.example.survey.exception.EmailAlreadyExistsException;
import com.example.survey.exception.PasswordsDoNotMatchException;
import com.example.survey.exception.UserNotFoundException;
import com.example.survey.repository.UserRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(RegisterDTO registerDTO) {
        if (userRepository.existsByEmail(registerDTO.getEmail())) {
            throw new EmailAlreadyExistsException();
        }

        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())) {
            throw new PasswordsDoNotMatchException();
        }

        String encodedPassword = passwordEncoder.encode(registerDTO.getPassword());

        User user = new User();
        user.setEmail(registerDTO.getEmail());
        user.setName(registerDTO.getName());
        user.setPassword(encodedPassword);
        user.setRole(User.Role.USER);

        userRepository.save(user);
    }
}
