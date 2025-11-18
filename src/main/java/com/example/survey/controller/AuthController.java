package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.RegisterDTO;
import com.example.survey.dto.UserDTO;
import com.example.survey.entity.User;
import com.example.survey.mapper.UserMapper;
import com.example.survey.repository.UserRepository;
import com.example.survey.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthController(AuthService authService, UserRepository userRepository, UserMapper userMapper) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody @Valid RegisterDTO registerDTO) {
        authService.register(registerDTO);
        User user = userRepository.findByEmail(registerDTO.getEmail());
        UserDTO userDTO = userMapper.toDTO(user);
        return ApiResponse.created("User registered successfully", userDTO);
    }
}
