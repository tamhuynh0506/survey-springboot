package com.example.survey.service;

import com.example.survey.config.JwtConfigProperties;
import com.example.survey.dto.LoginDTO;
import com.example.survey.dto.RegisterDTO;
import com.example.survey.dto.UserDTO;
import com.example.survey.entity.RefreshToken;
import com.example.survey.entity.User;
import com.example.survey.exception.*;
import com.example.survey.mapper.UserMapper;
import com.example.survey.repository.RefreshTokenRepository;
import com.example.survey.repository.UserRepository;

import com.example.survey.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final JwtConfigProperties jwtConfigProperties;
    private final RefreshTokenRepository refreshTokenRepository;

    public Map<String, String> login(LoginDTO loginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));

        User user = userRepository.findByEmail(loginDTO.getEmail()).orElseThrow(
                () -> new NotFoundException("User"));

        String accessToken = jwtUtil.generateAccessToken(user.getEmail(), user.getRole().name());
        RefreshToken refreshToken = refreshTokenService.create(user, jwtConfigProperties.refresh().expirationMs());

        return Map.of("accessToken", accessToken, "refreshToken", refreshToken.getId());
    }

    public UserDTO register(RegisterDTO registerDTO) {
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
        user.setRole(registerDTO.getRole());

        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setVerificationTokenExpiry(new Date(System.currentTimeMillis() + 3600_000)); // 1hr

        userRepository.save(user);

        User createdUser = userRepository.findByEmail(registerDTO.getEmail())
                .orElseThrow(() -> new ApiException("Error when create user"));

        return userMapper.toDTO(createdUser);
    }

    public void verify(String token) {
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(InvalidTokenException::new);

        if (user.getVerificationTokenExpiry().before(new Date())) {
            throw new TokenExpiredException();
        }

        user.setEmailVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);
    }

    public String forgotPassword(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("User"));

        String token = UUID.randomUUID().toString();
        user.setResetPasswordToken(token);
        user.setResetPasswordExpiry(new Date(System.currentTimeMillis() + 3600_000)); // 1hr
        userRepository.save(user);
        return "http://localhost:8080/auth/reset-password?token=" + token;
    }

    public void resetPassword(String token, String newPassword) {
        User user = userRepository.findByResetPasswordToken(token).orElseThrow(
                () -> new NotFoundException("User"));

        if (user.getResetPasswordExpiry().before(new Date())) {
            throw new TokenExpiredException();
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetPasswordToken(null);

        userRepository.save(user);
    }

    public Map<String, String> refresh(String refreshToken) {
        RefreshToken storedToken = refreshTokenRepository.findById(refreshToken)
                .orElseThrow(InvalidTokenException::new);

        String email = jwtUtil.extractEmailFromRefresh(storedToken.getId());
        User user = userRepository.findByEmail(email).orElseThrow();

        String accessToken = jwtUtil.generateAccessToken(email, user.getRole().toString());
        String newRefresh = jwtUtil.generateRefreshToken(email);

        storedToken.setId(newRefresh);
        storedToken.setExpiryDate(new Date(System.currentTimeMillis() + 3600_000));
        refreshTokenRepository.save(storedToken);

        return Map.of("accessToken", accessToken, "newRefreshToken", storedToken.getId());
    }
}
