package com.project.services;

import com.project.model.Task;
import com.project.repositories.TaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Task service")
public class TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> findById(Long ID) {
        return taskRepository.findById(ID);
    }

    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

    public boolean existsById(Long ID) {
        return taskRepository.existsById(ID);
    }

    public long count() {
        return taskRepository.count();
    }

    public void delete(Task task) {
        taskRepository.delete(task);
    }
}
