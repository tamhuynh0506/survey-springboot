package com.example.survey.util;

import com.example.survey.config.JwtConfigProperties;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {
    private final JwtConfigProperties jwtConfigProperties;

    public JwtUtil(JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
    }

    public String generateAccessToken(String email, String role) {
        return Jwts.builder().subject(email)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfigProperties.access().expirationMs()))
                .signWith(Keys.hmacShaKeyFor(jwtConfigProperties.access().secret().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder().subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfigProperties.refresh().expirationMs()))
                .signWith(Keys.hmacShaKeyFor(jwtConfigProperties.refresh().secret().getBytes(StandardCharsets.UTF_8)))
                .compact();
    }
}
