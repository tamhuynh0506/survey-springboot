package com.example.survey.service;

import com.example.survey.dto.QuestionDTO;
import com.example.survey.entity.Question;
import com.example.survey.exception.NotFoundException;
import com.example.survey.mapper.QuestionMapper;
import com.example.survey.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionDTO getById(UUID id) {
        Question question = questionRepository.findById(id).orElseThrow(
            () -> new NotFoundException(Question.class));
        return questionMapper.toDTO(question);
    }
}
