package com.example.survey.mapper;

import com.example.survey.dto.QuestionDTO;
import com.example.survey.entity.Question;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface QuestionMapper {
    QuestionDTO toDTO(Question question);

    Question toEntity(QuestionDTO questionDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateQuestion(QuestionDTO dto, @MappingTarget Question question);
}
