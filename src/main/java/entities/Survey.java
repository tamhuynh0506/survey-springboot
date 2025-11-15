package entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
public class Survey {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private boolean published;
    private Instant createdAt = Instant.now();

    @ManyToOne
    private User createdBy;

    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<Question> questions;
}