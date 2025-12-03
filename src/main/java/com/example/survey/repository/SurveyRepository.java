package com.example.survey.repository;

import com.example.survey.dto.dashboard.DashboardSurveysOverviewDTO;
import com.example.survey.entity.Survey;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
import java.util.UUID;

public interface SurveyRepository extends JpaRepository<Survey, UUID> {
    Optional<Survey> findById(UUID id);
    long countByPublished(boolean isPublished);

    @Query("""
        SELECT new com.example.survey.dto.dashboard.DashboardSurveysOverviewDTO (
            s.id,
            s.title,
            SIZE(s.questions),
            COUNT(us),
            s.published,
            MAX(us.submittedAt)
        )
        FROM Survey s
        LEFT JOIN s.questions q
        LEFT JOIN s.userSubmissions us
        GROUP BY s.id
        ORDER BY s.createdAt DESC
    """)
    Page<DashboardSurveysOverviewDTO> findSurveyOverview(Pageable pageable);
}
