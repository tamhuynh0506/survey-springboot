package com.example.survey.service;

import com.example.survey.dto.SurveyDTO;
import com.example.survey.entity.Survey;
import com.example.survey.exception.NotFoundException;
import com.example.survey.mapper.SurveyMapper;
import com.example.survey.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyDTO getById(UUID id) {
        Survey survey = surveyRepository.findById(id)
                .orElseThrow(NotFoundException::new);

        return surveyMapper.toDTO(survey);
    }

    public UUID create(SurveyDTO dto) {
        Survey survey = surveyMapper.toEntity(dto);
        surveyRepository.save(survey);
        return survey.getId();
    }

    public Page<SurveyDTO> getAllSurveys(int page, int size, String sortBy, String direction) {

        Sort sort = "desc".equalsIgnoreCase(direction)
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Survey> surveyPage = surveyRepository.findAll(pageable);

        return surveyMapper.toDtoPage(surveyPage);
    }
}
