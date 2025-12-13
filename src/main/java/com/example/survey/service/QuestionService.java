package com.example.survey.service;

import com.example.survey.dto.QuestionDTO;
import com.example.survey.entity.Question;
import com.example.survey.mapper.QuestionMapper;
import com.example.survey.repository.QuestionRepository;
import com.example.survey.util.FetchUtil;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;

    public QuestionDTO getById(UUID id) {
        Question question = FetchUtil.orThrow(questionRepository.findById(id), Question.class);
        return questionMapper.toDTO(question);
    }
}
