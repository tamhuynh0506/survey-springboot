package com.example.survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue
    private UUID id;
    private String title;
    private String description;
    private Instant createdAt = Instant.now();
    private UUID createdBy;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions;

    @OneToMany(mappedBy = "survey")
    private List<UserSubmission> userSubmissions;

    @Enumerated(EnumType.STRING)
    private SurveyStatus status = SurveyStatus.DRAFT;

    public enum SurveyStatus {
        DRAFT,      // Created but not visible
        PUBLISHED,  // Live and accepting responses
        CLOSED      // No longer accepts responses
    }

    private Instant publishedAt;
    private Instant closedAt;

    public void publish(Instant time) {
        this.status = SurveyStatus.PUBLISHED;
        this.publishedAt = time;
    }
}