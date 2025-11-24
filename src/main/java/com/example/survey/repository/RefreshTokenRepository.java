package com.example.survey.repository;

import com.example.survey.entity.RefreshToken;
import com.example.survey.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    List<RefreshToken> findByUser(User user);
    Optional<RefreshToken> findById(String token);
}
