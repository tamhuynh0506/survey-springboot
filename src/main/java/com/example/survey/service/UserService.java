package com.example.survey.service;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.survey.dto.UserDTO;
import com.example.survey.entity.User;
import com.example.survey.exception.NotFoundException;
import com.example.survey.mapper.UserMapper;
import com.example.survey.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    
    public UserDTO getCurrentUser(UUID userId) {
       User user = userRepository.findById(userId).orElseThrow(
        () -> new NotFoundException(User.class));
       return userMapper.toDTO(user);
    }
}
