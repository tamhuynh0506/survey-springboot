package com.example.survey.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SurveyDTO {
    private UUID id;
    private String title;
    private String description;
    private boolean published;
    private List<QuestionDTO> questions;
}
