package com.project.repositories;

import com.project.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectsRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByStudents_ID(Long id);
}
