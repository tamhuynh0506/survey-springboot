package com.example.survey.controller;

import com.example.survey.ApiResponse;
import com.example.survey.dto.dashboard.DashboardHighLevelDTO;
import com.example.survey.dto.dashboard.DashboardSurveysOverviewDTO;
import com.example.survey.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/surveys-overview")
    public ResponseEntity<ApiResponse<Page<DashboardSurveysOverviewDTO>>> getSurveysOverview(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "title") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {
        return ApiResponse.success("Success", dashboardService.getSurveysOverview(page, size, sortBy, direction));
    }
}
