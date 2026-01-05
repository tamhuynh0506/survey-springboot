package com.example.survey.dto;

import java.util.UUID;

import com.example.survey.enums.SurveyStatus;

public record InvalidSurveyPublishItem(
        UUID surveyId,
        String title,
        SurveyStatus currentStatus) {
}
