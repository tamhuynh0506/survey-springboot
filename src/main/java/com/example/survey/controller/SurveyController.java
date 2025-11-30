package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.SurveyDTO;
import com.example.survey.service.SurveyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/survey")
public class SurveyController {

    private final SurveyService surveyService;

    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyDTO>> get(@PathVariable UUID id) {
        return ApiResponse.success("Success", surveyService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<UUID>> create(@Valid @RequestBody SurveyDTO dto) {
        return ApiResponse.created("Create survey successfully", surveyService.create(dto));
    }
}
