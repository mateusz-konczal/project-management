package com.project.services;

import com.project.model.Student;
import com.project.repositories.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Students service")
public class StudentsService {

    private final StudentsRepository studentsRepository;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    public Optional<Student> findById(Long ID) {
        return studentsRepository.findById(ID);
    }

    public Student save(Student student) {
        return studentsRepository.save(student);
    }

    public List<Student> saveAll(Iterable<Student> students) {
        return studentsRepository.saveAll(students);
    }

    public boolean existsById(Long ID) {
        return studentsRepository.existsById(ID);
    }

    public long count() {
        return studentsRepository.count();
    }

    public void delete(Student student) {
        studentsRepository.delete(student);
    }

}
