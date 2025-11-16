package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Question question;

    @ManyToOne
    private UserSubmission userSubmission;

    @ElementCollection
    private List<String> value; // supports multiple choices
}