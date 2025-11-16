package com.example.survey.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Question {
    @Id
    @GeneratedValue
    private UUID id;

    private String text;

    @Enumerated(EnumType.STRING)
    private Type type;

    @ElementCollection
    private List<String> options;

    @ManyToOne
    private Survey survey;

    public enum Type {
        TEXT, SINGLE_CHOICE, MULTIPLE_CHOICE
    }
}
