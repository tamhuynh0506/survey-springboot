package dtos;

import entities.Question;

import java.util.List;
import java.util.UUID;

public class QuestionDTO {
    private UUID id;
    private String text;
    private List<String> options;
    private Question.Type type;
    private SurveyDTO survey;
}
