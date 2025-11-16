package dto;

import java.util.List;
import java.util.UUID;

public class AnswerDTO {
    private UUID id;
    private QuestionDTO question;
    private ResponseDTO response;
    private List<String> value;
}
