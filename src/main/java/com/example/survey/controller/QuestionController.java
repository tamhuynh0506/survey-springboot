package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.QuestionDTO;
import com.example.survey.service.QuestionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<QuestionDTO>> get(@PathVariable UUID id) {
        return ApiResponse.success("Success", questionService.getById(id));
    }
}
