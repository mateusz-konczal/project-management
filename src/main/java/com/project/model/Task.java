package com.project.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks", indexes = {@Index(name = "idx_task_name", columnList = "name")})
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;

    @Column(nullable = false, length = 50)
    private String name;

    private Integer sequence;

    @Column(length = 1000)
    private String description;

    @CreationTimestamp
    @Column(name = "addition_date_time", nullable = false, updatable = false)
    private LocalDateTime additionDateTime;

    public Task() {
    }

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, Integer sequence) {
        this.name = name;
        this.sequence = sequence;
    }

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Task(String name, Integer sequence, String description) {
        this.name = name;
        this.sequence = sequence;
        this.description = description;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getAdditionDateTime() {
        return additionDateTime;
    }

    public void setAdditionDateTime(LocalDateTime additionDateTime) {
        this.additionDateTime = additionDateTime;
    }

}
