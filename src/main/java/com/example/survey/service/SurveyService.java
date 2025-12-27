package com.example.survey.service;

import com.example.survey.dto.InvalidSurveyPublishItem;
import com.example.survey.dto.SurveyDTO;
import com.example.survey.entity.Survey;
import com.example.survey.entity.Survey.SurveyStatus;
import com.example.survey.exception.BulkSurveyPublishException;
import com.example.survey.exception.NotFoundException;
import com.example.survey.mapper.SurveyMapper;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.util.PageUtil;
import com.example.survey.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyService {
    private final SurveyRepository surveyRepository;
    private final SurveyMapper surveyMapper;

    public SurveyDTO getById(UUID id) {
        Survey survey = surveyRepository.findById(id).orElseThrow(
            () -> new NotFoundException(Survey.class));
        return surveyMapper.toDTO(survey);
    }

    public UUID create(SurveyDTO dto) {
        Survey survey = surveyMapper.toEntity(dto);
        survey.setCreatedBy(SecurityUtil.getCurrentUserId());
        surveyRepository.save(survey);
        return survey.getId();
    }

    public Page<SurveyDTO> getAllSurveys(int page, int size, String sortBy, String direction) {
        Pageable pageable = PageUtil.buildPageable(page, size, sortBy, direction);
        Page<Survey> surveyPage = surveyRepository.findAll(pageable);
        return surveyMapper.toDtoPage(surveyPage);
    }

    @Transactional
    public void publishSurveys(List<UUID> surveyIds) {
        List<Survey> surveys = surveyRepository.findAllById(surveyIds);

        List<InvalidSurveyPublishItem> invalidItems =
        surveys.stream()
                .filter(survey -> survey.getStatus() != SurveyStatus.DRAFT)
                .map(this::toInvalidItem)
                .toList();

        if (!invalidItems.isEmpty()) {
            throw new BulkSurveyPublishException(invalidItems);
        }

        Instant now = Instant.now();
        surveys.forEach(survey -> survey.publish(now));

        surveyRepository.saveAll(surveys);
    }

    private InvalidSurveyPublishItem toInvalidItem(Survey survey) {
        return new InvalidSurveyPublishItem(
                survey.getId(),
                survey.getTitle(),
                survey.getStatus()
        );
    }
}
