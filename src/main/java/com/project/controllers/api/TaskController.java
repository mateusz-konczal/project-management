package com.project.controllers.api;

import com.project.model.Task;
import com.project.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
@CrossOrigin
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAllTasks() {
        List<Task> allTasks = taskService.findAll();
        return new ResponseEntity<>(allTasks, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> findById(@PathVariable("id") long id) {
        Optional<Task> task = taskService.findById(id);

        if (task.isPresent()) {
            return new ResponseEntity<>(task.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Task> create(@RequestBody Task task) {
        task = taskService.create(task);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Task> update(@RequestBody Task task) {
        task = taskService.update(task);

        if (task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
