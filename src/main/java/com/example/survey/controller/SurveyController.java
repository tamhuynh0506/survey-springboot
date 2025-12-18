package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.SurveyDTO;
import com.example.survey.service.SurveyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/surveys")
@RequiredArgsConstructor
public class SurveyController {

    private final SurveyService surveyService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<SurveyDTO>> get(@PathVariable UUID id) {
        return ApiResponse.success("Success", surveyService.getById(id));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ApiResponse<UUID>> create(@Valid @RequestBody SurveyDTO dto) {
        return ApiResponse.created("Create survey successfully", surveyService.create(dto));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<ApiResponse< Page<SurveyDTO>>> getSurveys(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ) {
        return ApiResponse.success("Success", surveyService.getAllSurveys(page, size, sortBy, direction));
    }
}
