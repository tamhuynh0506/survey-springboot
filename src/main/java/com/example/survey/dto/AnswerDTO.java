package com.example.survey.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class AnswerDTO {
    private UUID id;
    private QuestionDTO question;
    private UserSubmissionDTO userSubmission;
    private List<String> value;
}
