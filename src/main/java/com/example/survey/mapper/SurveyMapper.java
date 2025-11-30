package com.example.survey.mapper;

import com.example.survey.dto.SurveyDTO;
import com.example.survey.entity.Survey;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = { QuestionMapper.class })
public interface SurveyMapper {

    SurveyDTO toDTO(Survey survey);

    Survey toEntity(SurveyDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateSurvey(SurveyDTO dto, @MappingTarget Survey survey);

    @AfterMapping
    default void linkQuestions(@MappingTarget Survey survey) {
        if (survey.getQuestions() != null) {
            survey.getQuestions().forEach(q -> q.setSurvey(survey));
        }
    }
}
