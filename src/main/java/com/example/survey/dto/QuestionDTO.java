package com.example.survey.dto;

import com.example.survey.entity.Question;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class QuestionDTO {
    private UUID id;
    private String text;
    private List<String> options;
    private Question.Type type;
    private SurveyDTO survey;
}
