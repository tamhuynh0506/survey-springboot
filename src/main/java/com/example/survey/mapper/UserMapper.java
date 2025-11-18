package com.example.survey.mapper;

import com.example.survey.dto.UserDTO;
import com.example.survey.entity.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);

    User toEntity(UserDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateUser(UserDTO dto, @MappingTarget User user);
}
