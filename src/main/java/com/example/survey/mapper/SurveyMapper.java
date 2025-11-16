package com.example.survey.mapper;

import com.example.survey.dto.SurveyDTO;
import com.example.survey.entity.Survey;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface SurveyMapper {
    SurveyDTO toDTO(Survey survey);

    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    Survey toEntity(SurveyDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    void updateSurvey(SurveyDTO dto, @MappingTarget Survey survey);
}
