package dtos;

import entities.Question;

import java.util.List;
import java.util.UUID;

public class SurveyDTO {
    private UUID id;
    private String title;
    private String description;
    private boolean published;
    private List<QuestionDTO> questions;
}
