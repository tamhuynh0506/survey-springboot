package com.example.survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.example.survey.enums.QuestionType;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue
    private UUID id;

    private String text;

    @Enumerated(EnumType.STRING)
    private QuestionType type;

    @ElementCollection
    private List<String> options = new ArrayList<>();

    @ManyToOne
    private Survey survey;
}
