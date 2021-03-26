package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToOne
    @JoinColumn(nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Project project;

    public Task(@NotNull String name, Integer sequence, String description, TaskStatus taskStatus) {
        this.name = name;
        this.sequence = sequence;
        this.description = description;
        this.taskStatus = taskStatus;
    }
}
