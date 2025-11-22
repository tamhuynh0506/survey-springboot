package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.RegisterDTO;
import com.example.survey.dto.UserDTO;
import com.example.survey.entity.User;
import com.example.survey.exception.ApiException;
import com.example.survey.exception.InvalidTokenException;
import com.example.survey.exception.TokenExpiredException;
import com.example.survey.exception.UserNotFoundException;
import com.example.survey.mapper.UserMapper;
import com.example.survey.repository.UserRepository;
import com.example.survey.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, UserRepository userRepository, UserMapper userMapper,
                          PasswordEncoder passwordEncoder) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody @Valid RegisterDTO registerDTO) {
        authService.register(registerDTO);
        User user = userRepository.findByEmail(registerDTO.getEmail())
                .orElseThrow(() -> new ApiException("Error when create user"));
        UserDTO userDTO = userMapper.toDTO(user);
        return ApiResponse.created("User registered successfully", userDTO);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String token) {
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(InvalidTokenException::new);

        if (user.getVerificationTokenExpiry().before(new Date())) {
            throw new TokenExpiredException();
        }

        user.setEmailVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);

        return ApiResponse.success("Email verified!", null);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgot(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(UserNotFoundException::new);

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setResetPasswordExpiry(new Date(System.currentTimeMillis() + 3600_000)); // 1hr
        userRepository.save(user);

        // send link via email (for now just show)
        return ApiResponse.success("Success",
                Map.of("resetLink", "http://localhost:8080/auth/reset-password?token=" + token));
    }

    @PostMapping("reset-password")
    public ResponseEntity<?> reset(@RequestParam String token, @RequestBody Map<String, String> body) {
        User user = userRepository.findByResetPasswordToken(token)
                .orElseThrow(UserNotFoundException::new);

        if (user.getResetPasswordExpiry().before(new Date())) {
            throw new TokenExpiredException();
        }
        String newPassword = body.get("newPassword");

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);

        userRepository.save(user);

        return ApiResponse.success("Reset password successfully", null);
    }

}
