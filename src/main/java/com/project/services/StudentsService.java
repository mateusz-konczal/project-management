package com.project.services;

import com.project.model.Project;
import com.project.model.Student;
import com.project.repositories.ProjectsRepository;
import com.project.repositories.StudentsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
@Slf4j(topic = "Students service")
public class StudentsService {

    private final StudentsRepository studentsRepository;
    private final ProjectsRepository projectsRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public StudentsService(StudentsRepository studentsRepository, ProjectsRepository projectsRepository, PasswordEncoder passwordEncoder) {
        this.studentsRepository = studentsRepository;
        this.projectsRepository = projectsRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<Student> findAll() {
        return studentsRepository.findAll();
    }

    public Optional<Student> findById(Long ID) {
        return studentsRepository.findById(ID);
    }

    public Student create(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentsRepository.save(student);
    }

    public Student update(Student student) {
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentsRepository.save(student);
    }

    public List<Student> saveAll(Iterable<Student> students) {
        for (Student student : students) {
            student.setPassword(passwordEncoder.encode(student.getPassword()));
        }
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

    public void deleteById(Long id) {
        if (existsById(id)) {
            List<Project> projects = projectsRepository.findAllByStudents_ID(id).stream()
                    .map((Project project) -> {
                                project.setStudents(project.getStudents()
                                        .stream()
                                        .filter(student -> !student.getID().equals(id))
                                        .collect(Collectors.toSet())
                                );
                                return project;
                            }
                    )
                    .collect(Collectors.toList());
            projectsRepository.saveAll(projects);

            studentsRepository.deleteById(id);
        }
    }

}
