package com.example.survey.util;

import com.example.survey.config.JwtConfigProperties;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtil {
    private final JwtConfigProperties jwtConfigProperties;
    private final SecretKey ACCESS_KEY;
    private final SecretKey REFRESH_KEY;

    public JwtUtil(JwtConfigProperties jwtConfigProperties) {
        this.jwtConfigProperties = jwtConfigProperties;
        this.ACCESS_KEY = Keys.hmacShaKeyFor(jwtConfigProperties.access().secret().getBytes(StandardCharsets.UTF_8));
        this.REFRESH_KEY = Keys.hmacShaKeyFor(jwtConfigProperties.refresh().secret().getBytes(StandardCharsets.UTF_8));
    }

    public String generateAccessToken(UUID userId, String email, String role) {
        return Jwts.builder().subject(userId.toString())
                .claim("role", role)
                .claim("email", email)  
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfigProperties.access().expirationMs()))
                .signWith(ACCESS_KEY)
                .compact();
    }

    public String generateRefreshToken(UUID userId) {
        return Jwts.builder().subject(userId.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtConfigProperties.refresh().expirationMs()))
                .signWith(REFRESH_KEY)
                .compact();
    }

    public UUID extractUserIdFromAccess(String accessToken) {
        return UUID.fromString(
            Jwts.parser()
                .verifyWith(ACCESS_KEY)
                .build()
                .parseSignedClaims(accessToken)
                .getPayload()
                .getSubject()
        );
    }

    public UUID extractUserIdFromRefresh(String refreshToken) {
        return UUID.fromString(
            Jwts.parser()
                .verifyWith(REFRESH_KEY)
                .build()
                .parseSignedClaims(refreshToken)
                .getPayload()
                .getSubject()
        );
    }

    public String extractEmailFromAccess(String accessToken) {
        return extractAllClaims(accessToken).get("email", String.class);
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(ACCESS_KEY)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isAccessTokenValid(String token) {
        try {
            Jwts.parser().verifyWith(ACCESS_KEY).build().parseSignedClaims(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    private boolean isTokenExpired(String token) {
        Date exp = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(jwtConfigProperties.access().secret().getBytes(StandardCharsets.UTF_8)))
                .build().parseSignedClaims(token).getPayload()
                .getExpiration();
        return exp.before(new Date());
    }
}
