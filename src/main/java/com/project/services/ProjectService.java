package com.project.services;

import com.project.model.Project;
import com.project.repositories.ProjectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Project service")
public class ProjectService {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Optional<Project> findById(Long ID) {
        return projectRepository.findById(ID);
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Project create(Project project) {
        return projectRepository.save(project);
    }

    public Project update(Project project) {
        return projectRepository.save(project);
    }

    public boolean existsById(Long ID) {
        return projectRepository.existsById(ID);
    }

    public long count() {
        return projectRepository.count();
    }

    public void delete(Project project) {
        projectRepository.delete(project);
    }
}
