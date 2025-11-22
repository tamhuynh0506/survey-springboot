package com.example.survey.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Getter
@Setter
public class RefreshToken {
    @Id
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    private Date expiryDate;

    private boolean revoked = false;
}
