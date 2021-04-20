package com.project.repositories;

import com.project.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TasksRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByProjectID(Long projectID);
}
