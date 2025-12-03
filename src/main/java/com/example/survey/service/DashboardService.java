package com.example.survey.service;

import com.example.survey.dto.dashboard.DashboardHighLevelDTO;
import com.example.survey.dto.dashboard.DashboardSurveysOverviewDTO;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UserRepository;
import com.example.survey.repository.UserSubmissionRepository;
import com.example.survey.util.PageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final SurveyRepository surveyRepository;
    private final UserSubmissionRepository userSubmissionRepository;
    private final UserRepository userRepository;

    public DashboardHighLevelDTO getHighLevel() {
        return new DashboardHighLevelDTO(
                surveyRepository.count(),
                surveyRepository.countByPublished(true),
                userSubmissionRepository.count(),
                userRepository.count()
        );
    }

    public Page<DashboardSurveysOverviewDTO> getSurveysOverview(int page, int size, String sortBy, String direction) {
        Pageable pageable = PageUtil.buildPageable(page, size, sortBy, direction);
        return surveyRepository.findSurveyOverview(pageable);
    }
}
