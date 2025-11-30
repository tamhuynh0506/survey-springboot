package com.example.survey.service;

import com.example.survey.entity.RefreshToken;
import com.example.survey.entity.User;
import com.example.survey.exception.InvalidTokenException;
import com.example.survey.exception.TokenExpiredException;
import com.example.survey.exception.TokenRevokedException;
import com.example.survey.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshToken create(User user, long refreshExpMs) {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUser(user);
        refreshToken.setExpiryDate(new Date(System.currentTimeMillis() + refreshExpMs));
        return refreshTokenRepository.save(refreshToken);
    }

    public RefreshToken verify(String tokenId) {
        RefreshToken refreshToken = refreshTokenRepository.findById(tokenId)
                .orElseThrow(InvalidTokenException::new);

        if (refreshToken.isRevoked()) throw new TokenRevokedException();
        if (refreshToken.getExpiryDate().before(new Date())) throw new TokenExpiredException();

        return refreshToken;
    }

    public void revoke(String tokenId) {
        refreshTokenRepository.findById(tokenId).ifPresent(rt -> {
            rt.setRevoked(true);
            refreshTokenRepository.save(rt);
        });
    }

    public void revokeAllByUser(User user) {
        List<RefreshToken> tokens = refreshTokenRepository.findByUser(user);
        tokens.forEach(t -> t.setRevoked(true));
        refreshTokenRepository.saveAll(tokens);
    }
}
