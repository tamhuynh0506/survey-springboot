package com.example.survey.service;

import com.example.survey.dto.QuestionDTO;
import com.example.survey.entity.Question;
import com.example.survey.exception.NotFoundException;
import com.example.survey.mapper.QuestionMapper;
import com.example.survey.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionService(QuestionRepository questionRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
    }

    public QuestionDTO getById(UUID id) {
        Question question = questionRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        return questionMapper.toDTO(question);
    }
}
