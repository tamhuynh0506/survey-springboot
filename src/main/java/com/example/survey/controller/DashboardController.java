package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.dashboard.DashboardHighLevelDTO;
import com.example.survey.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/admin/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/high-level")
    public ResponseEntity<ApiResponse<DashboardHighLevelDTO>> getHighLevel() {
        return ApiResponse.success("Success", dashboardService.getHighLevel());
    }
}
