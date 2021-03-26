package com.project.services;

import com.project.model.Student;
import com.project.repositories.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j(topic = "Student service")
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    public Optional<Student> findById(Long ID) {
        return studentRepository.findById(ID);
    }

    public Student save(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> saveAll(Iterable<Student> students) {
        return studentRepository.saveAll(students);
    }

    public boolean existsById(Long ID) {
        return studentRepository.existsById(ID);
    }

    public long count() {
        return studentRepository.count();
    }

    public void delete(Student student) {
        studentRepository.delete(student);
    }

}
