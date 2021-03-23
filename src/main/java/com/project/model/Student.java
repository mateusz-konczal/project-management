package com.project.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "students")
public class Student {
    @Id private Integer ID;
    @NotNull private String firstName;
    @NotNull private String lastName;
    @Column(unique = true) private String indexNumber;
    @Email private String email;
    @NotNull private Boolean fullTime = Boolean.TRUE;
}
