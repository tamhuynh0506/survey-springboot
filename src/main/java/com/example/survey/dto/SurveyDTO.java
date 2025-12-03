package com.example.survey.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class SurveyDTO {
    private UUID id;
    @NotBlank(message = "Title must not be empty")
    private String title;
    @NotBlank(message = "Description must not be empty")
    private String description;
    private boolean published = Boolean.FALSE;
    @NotEmpty(message = "Questions list must not be empty")
    private List<QuestionDTO> questions;
    private UUID createdBy;
}
