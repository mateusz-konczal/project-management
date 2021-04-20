package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "projects")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ID;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 1000)
    private String description;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime creationDateTime;

    private LocalDate deliveryDate;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Student> students;

    public Project(String name, String description, LocalDate deliveryDate) {
        this.name = name;
        this.description = description;
        this.deliveryDate = deliveryDate;
    }
}
