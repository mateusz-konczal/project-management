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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @ManyToOne
    private Project project;

    @NotNull
    private String name;

    private Integer sequence;

    @Lob
    private String description;

    @Enumerated
    @NotNull
    private TaskStatus taskStatus = TaskStatus.TO_DO;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime additionDateTime;
}
