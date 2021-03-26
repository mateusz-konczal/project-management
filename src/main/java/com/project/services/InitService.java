package com.project.services;

import com.project.model.Project;
import com.project.model.Student;
import com.project.model.Task;
import com.project.model.TaskStatus;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

@Service
@Slf4j(topic = "Init service")
public class InitService {

    private final ProjectService projectService;
    private final TaskService taskService;
    private final StudentService studentService;

    @Autowired
    public InitService(ProjectService projectService, TaskService taskService, StudentService studentService) {
        this.projectService = projectService;
        this.taskService = taskService;
        this.studentService = studentService;
    }

    @PostConstruct
    public void initData() {
        log.info("Starting initializing data...");

        final int PROJECT_NUMBER = 20;
        final int MIN_STUDENTS_IN_PROJECT = 4;
        final int MAX_STUDENTS_IN_PROJECT = 5;
        final int MIN_DELIVERY_DAYS = 14;
        final int MAX_DELIVERY_DAYS = 90;

        final int MIN_TASKS_IN_PROJECT = 5;
        final int MAX_TASKS_IN_PROJECT = 10;

        Lorem lorem = LoremIpsum.getInstance();

        Student s1 = new Student(0L, "John", "Smith", "100000", "Smith@example.com", true);
        Student s2 = new Student(0L, "Lara", "Baxter", "100001", "Baxter@example.com", true);
        Student s3 = new Student(0L, "Alex", "Tyson", "100002", "Tyson@example.com", true);
        Student s4 = new Student(0L, "Sam", "Wright", "100004", "Wright@example.com", false);
        Student s5 = new Student(0L, "Charlotte", "Marshall", "100005", "Marshall@example.com", true);
        Student s6 = new Student(0L, "William", "Barker", "100006", "Barker@example.com", true);
        Student s7 = new Student(0L, "Lucas", "Wolfe", "100007", "Wolfe@example.com", false);
        Student s8 = new Student(0L, "Oliver", "Harvey", "100008", "Harvey@example.com", true);
        Student s9 = new Student(0L, "Emma", "Simon", "100009", "Simon@example.com", false);
        Student s10 = new Student(0L, "Benjamin", "Bourn", "100010", "Bourn@example.com", true);
        List<Student> allStudents = studentService.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));

        for (int i = 0; i < PROJECT_NUMBER; i++) {
            String name = lorem.getTitle(2, 4);
            String description = lorem.getWords(10, 20);

            boolean isDelivered = ThreadLocalRandom.current().nextBoolean();
            LocalDate deliveryLocalDate = null;
            if (isDelivered) {
                int deliveryDays = ThreadLocalRandom.current().nextInt(MIN_DELIVERY_DAYS, MAX_DELIVERY_DAYS + 1);
                deliveryLocalDate = LocalDate.now().plusDays(deliveryDays);
            }

            Set<Student> studentsInProjectSet = new HashSet<>();
            int studentsInProject = ThreadLocalRandom.current()
                    .nextInt(MIN_STUDENTS_IN_PROJECT, MAX_STUDENTS_IN_PROJECT + 1);
            for (int j = 0; j < studentsInProject; j++) {
                studentsInProjectSet.add(
                        allStudents.get(ThreadLocalRandom.current().nextInt(allStudents.size()))
                );
            }

            Project project = new Project(name, description, deliveryLocalDate);
            project.setStudents(studentsInProjectSet);
            project = projectService.create(project);

            int tasksInProject = ThreadLocalRandom.current()
                    .nextInt(MIN_TASKS_IN_PROJECT, MAX_TASKS_IN_PROJECT + 1);
            for (int k = 0; k < tasksInProject; k++) {
                String taskName = lorem.getTitle(2, 4);
                String taskDescription = lorem.getWords(10, 20);
                TaskStatus taskStatus = TaskStatus.values()[ThreadLocalRandom.current().nextInt(TaskStatus.values().length)];

                Task t = new Task(taskName, k + 1, taskDescription, taskStatus);
                t.setProject(project);
                taskService.create(t);
            }
        }

        log.info("Finished initializing data");
    }
}
