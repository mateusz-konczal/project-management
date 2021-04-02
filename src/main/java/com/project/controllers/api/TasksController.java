package com.project.controllers.api;

import com.project.model.Task;
import com.project.services.TasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
@CrossOrigin
public class TasksController {
    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAllTasks() {
        List<Task> allTasks = tasksService.findAll();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") long id) {
        Optional<Task> task = tasksService.findById(id);

        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allByProject/{projectID}")
    public ResponseEntity<List<Task>> findAllByProjectID(@PathVariable("projectID") long projectID) {
        List<Task> allByProjectID = tasksService.findAllByProjectID(projectID);
        return new ResponseEntity<>(allByProjectID, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody Task task) {
        task = tasksService.create(task);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Task> update(@RequestBody Task task) {
        task = tasksService.update(task);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        tasksService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}