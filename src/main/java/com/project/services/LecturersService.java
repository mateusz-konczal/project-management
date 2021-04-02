package com.project.services;

import com.project.model.Lecturer;
import com.project.model.Project;
import com.project.model.Student;
import com.project.repositories.LecturersRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j(topic = "Lecturers service")
public class LecturersService {
    private final LecturersRepository lecturersRepository;

    @Autowired
    public LecturersService(LecturersRepository lecturersRepository) {
        this.lecturersRepository = lecturersRepository;
    }

    public List<Lecturer> findAll() {
        return lecturersRepository.findAll();
    }

    public Optional<Lecturer> findById(Long ID) {
        return lecturersRepository.findById(ID);
    }

    public Lecturer create(Lecturer lecturer) {
        return lecturersRepository.save(lecturer);
    }

    public Lecturer update(Lecturer lecturer) {
        return lecturersRepository.save(lecturer);
    }

    public List<Lecturer> saveAll(Iterable<Lecturer> lecturers) {
        return lecturersRepository.saveAll(lecturers);
    }

    public boolean existsById(Long ID) {
        return lecturersRepository.existsById(ID);
    }

    public long count() {
        return lecturersRepository.count();
    }

    public void delete(Lecturer lecturer) {
        lecturersRepository.delete(lecturer);
    }

    public void deleteById(Long id) {
        if (existsById(id)) {
            lecturersRepository.deleteById(id);
        }
    }
}
