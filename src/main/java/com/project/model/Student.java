package com.project.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "students")
public class Student extends User {
    @Column(unique = true)
    private String indexNumber;

    @NotNull
    private Boolean fullTime = Boolean.TRUE;

    public Student(Long ID, String username, String password, String lastName, String firstName, String email, String indexNumber, Boolean fullTime) {
        super(ID, username, password, UserRole.STUDENT, lastName, firstName, email);
        this.indexNumber = indexNumber;
        this.fullTime = fullTime;
    }
}


