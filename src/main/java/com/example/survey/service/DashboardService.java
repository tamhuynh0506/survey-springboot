package com.example.survey.service;

import com.example.survey.dto.dashboard.DashboardHighLevelDTO;
import com.example.survey.repository.SurveyRepository;
import com.example.survey.repository.UserRepository;
import com.example.survey.repository.UserSubmissionRepository;
import lombok.RequiredArgsConstructor;
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
}
