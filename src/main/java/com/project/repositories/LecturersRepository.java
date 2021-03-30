package com.project.repositories;

import com.project.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LecturersRepository extends JpaRepository<Lecturer, Long> {
}
