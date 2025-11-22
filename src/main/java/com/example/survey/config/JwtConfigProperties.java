package com.example.survey.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@Validated
@ConfigurationProperties(prefix = "app.jwt")
public record JwtConfigProperties (
        TokenProperties access,
        TokenProperties refresh
){
    @Validated
    public record TokenProperties(
            @NotBlank String secret,
            @Positive long expirationMs
    ) {}
}
