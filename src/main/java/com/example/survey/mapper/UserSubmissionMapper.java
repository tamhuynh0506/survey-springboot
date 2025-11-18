package com.example.survey.mapper;

import com.example.survey.dto.UserSubmissionDTO;
import com.example.survey.entity.UserSubmission;
import org.mapstruct.*;

@Mapper(
        componentModel = "spring"
)
public interface UserSubmissionMapper {
    UserSubmissionDTO toDTO(UserSubmission userSubmission);

    UserSubmission toEntity(UserSubmissionDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUserSubmission(UserSubmissionDTO dto, @MappingTarget UserSubmission userSubmission);
}
