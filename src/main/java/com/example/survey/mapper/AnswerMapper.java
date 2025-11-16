package com.example.survey.mapper;

import com.example.survey.dto.AnswerDTO;
import com.example.survey.entity.Answer;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring"
)
public interface AnswerMapper {
    AnswerDTO toDTO(Answer answer);

    Answer toEntity(AnswerDTO answerDTO);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateAnswer(AnswerDTO dto, @MappingTarget Answer answer);
}
