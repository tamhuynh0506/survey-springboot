package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.LoginDTO;
import com.example.survey.dto.RegisterDTO;
import com.example.survey.dto.UserDTO;
import com.example.survey.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Map<String, String>>> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ApiResponse.success("Login successfully", authService.login(loginDTO));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserDTO>> register(@RequestBody @Valid RegisterDTO registerDTO) {
        UserDTO userDTO = authService.register(registerDTO);
        return ApiResponse.created("User registered successfully", userDTO);
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verify(@RequestParam String token) {
        authService.verify(token);
        return ApiResponse.success("Email verified!", null);
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgot(@RequestBody Map<String, String> body) {
        String url = authService.forgotPassword(body.get("email"));
        // send link via email (for now just show)
        return ApiResponse.success("Success", Map.of("resetLink", url));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> reset(@RequestParam String token, @RequestBody Map<String, String> body) {
        authService.resetPassword(token, body.get("newPassword"));
        return ApiResponse.success("Reset password successfully", null);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refresh(@RequestParam String refreshToken) {
        Map<String, String> newTokens = authService.refresh(refreshToken);
        return ApiResponse.success("Refresh successfully", newTokens);
    }
}
