package com.project.model;

import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Set;

@Entity
@Table(name = "students",
        indexes = {@Index(name = "idx_last_name", columnList = "last_name"),
                @Index(name = "idx_index_number", columnList = "index_number", unique = true)})
@ToString(exclude = {"projects"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Integer studentId;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "index_number", nullable = false, length = 20, unique = true)
    private String indexNumber;

    @Email
    @Column(length = 50)
    private String email;

    @Column(name = "full_time", nullable = false)
    private Boolean fullTime;

    @ManyToMany(mappedBy = "students", fetch = FetchType.EAGER)
    private Set<Project> projects;

    public Student() {
    }

    public Student(String firstName, String lastName, String indexNumber, Boolean fullTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
        this.fullTime = fullTime;
    }

    public Student(String firstName, String lastName, String indexNumber, String email, Boolean fullTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.indexNumber = indexNumber;
        this.email = email;
        this.fullTime = fullTime;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIndexNumber() {
        return indexNumber;
    }

    public void setIndexNumber(String indexNumber) {
        this.indexNumber = indexNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getFullTime() {
        return fullTime;
    }

    public void setFullTime(Boolean fullTime) {
        this.fullTime = fullTime;
    }

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

}
