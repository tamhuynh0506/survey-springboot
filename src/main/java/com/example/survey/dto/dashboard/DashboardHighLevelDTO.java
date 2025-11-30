package com.example.survey.dto.dashboard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DashboardHighLevelDTO {
    private long surveyCount;
    private long publishedSurveyCount;
    private long submissionCount;
    private long userCount;
}
