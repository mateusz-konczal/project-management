package com.project.controllers.api;

import com.project.model.Project;
import com.project.model.Task;
import com.project.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
@CrossOrigin
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Project>> findAllProjects() {
        List<Project> allProjects = projectService.findAll();
        return new ResponseEntity<>(allProjects, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Project> findById(@PathVariable("id") long id) {
        Optional<Project> project = projectService.findById(id);

        if (project.isPresent()) {
            return new ResponseEntity<>(project.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allByStudent/{studentID}")
    public ResponseEntity<List<Project>> findAllByStudents_ID(@PathVariable("studentID") long studentID) {
        List<Project> allByStudentsID = projectService.findAllByStudents_ID(studentID);
        return new ResponseEntity<>(allByStudentsID, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Project> create(@RequestBody Project project) {
        project = projectService.create(project);

        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Project> update(@RequestBody Project project) {
        project = projectService.update(project);

        if (project != null) {
            return new ResponseEntity<>(project, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        projectService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
