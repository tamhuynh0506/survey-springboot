package com.example.survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.example.survey.enums.SurveyStatus;

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

    private Instant publishedAt;
    private Instant closedAt;

    public void publish(Instant time) {
        this.status = SurveyStatus.PUBLISHED;
        this.publishedAt = time;
    }
}