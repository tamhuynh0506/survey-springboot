package com.example.survey.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.survey.ApiResponse;
import com.example.survey.service.UserService;
import com.example.survey.util.SecurityUtil;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser() {
        return ApiResponse.success("Get current user successfully",
                userService.getCurrentUser(SecurityUtil.getCurrentUserId()));
    }
}
