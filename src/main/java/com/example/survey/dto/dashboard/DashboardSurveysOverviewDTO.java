package com.example.survey.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class DashboardSurveysOverviewDTO {
    private UUID id;
    private String title;
    private long questionCount;
    private long submissionCount;
    private Boolean isPublished;
    private Instant lastSub;
}
