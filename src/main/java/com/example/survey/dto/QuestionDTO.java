package com.example.survey.dto;

import com.example.survey.enums.QuestionType;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class QuestionDTO {
    private UUID id;
    @NotBlank(message = "Question must not be empty")
    private String text;
    private List<String> options;
    private QuestionType type;
}
