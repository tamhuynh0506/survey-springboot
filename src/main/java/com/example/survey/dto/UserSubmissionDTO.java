package com.example.survey.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class UserSubmissionDTO {
    private UUID id;
    private SurveyDTO survey;
    private UserDTO user;
    private List<AnswerDTO> answers;
}
