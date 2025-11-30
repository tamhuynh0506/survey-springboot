package com.example.survey.repository;

import com.example.survey.entity.UserSubmission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserSubmissionRepository extends JpaRepository<UserSubmission, UUID> {
    long countBySurveyId(UUID surveyId);
}
