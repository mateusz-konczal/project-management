package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tasks")
public class Task {
    @Id private Integer ID;
    @NotNull private String name;
    private Integer sequence;
    @Lob private String description;
    @CreationTimestamp @Column(nullable = false, updatable = false) private LocalDateTime additionDateTime;
    @ManyToOne private Project project;
}
