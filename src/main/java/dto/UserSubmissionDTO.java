package dto;

import java.util.List;
import java.util.UUID;

public class UserSubmissionDTO {
    private UUID id;
    private SurveyDTO survey;
    private UserDTO user;
    private List<AnswerDTO> answers;
}
