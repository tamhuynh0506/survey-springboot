package com.example.survey.repository;

import com.example.survey.entity.Survey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SurveyRepository extends JpaRepository<Survey, UUID> {
    Optional<Survey> findById(UUID id);
    long countByPublished(boolean isPublished);
}
