package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class UserSubmission {
    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    private Survey survey;

    @ManyToOne
    private User user; // optional for anonymous

    private Instant submittedAt = Instant.now();

    @OneToMany(mappedBy = "response", cascade = CascadeType.ALL)
    private List<Answer> answers;
}