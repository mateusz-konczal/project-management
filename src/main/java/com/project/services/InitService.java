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

    private final ProjectsService projectsService;
    private final TasksService tasksService;
    private final StudentsService studentsService;

    @Autowired
    public InitService(ProjectsService projectsService, TasksService tasksService, StudentsService studentsService) {
        this.projectsService = projectsService;
        this.tasksService = tasksService;
        this.studentsService = studentsService;
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

        Student s1 = new Student(0L, "jsmith", "password", "Smith", "John", "Smith@example.com", "100000", true);
        Student s2 = new Student(0L, "lbaxter", "password", "Baxter", "Lara", "Baxter@example.com", "100001", true);
        Student s3 = new Student(0L, "atyson", "password", "Tyson", "Alex", "Tyson@example.com", "100002", true);
        Student s4 = new Student(0L, "swright", "password", "Wright", "Sam", "Wright@example.com", "100004", false);
        Student s5 = new Student(0L, "cmarshall", "password", "Marshall", "Charlotte", "Marshall@example.com", "100005", true);
        Student s6 = new Student(0L, "wbarker", "password", "Barker", "William", "Barker@example.com", "100006", true);
        Student s7 = new Student(0L, "lwolfe", "password", "Wolfe", "Lucas", "Wolfe@example.com", "100007", false);
        Student s8 = new Student(0L, "oharvey", "password", "Harvey", "Oliver", "Harvey@example.com", "100008", true);
        Student s9 = new Student(0L, "esimson", "password", "Simon", "Emma", "Simon@example.com", "100009", false);
        Student s10 = new Student(0L, "bbourn", "password", "Bourn", "Benjamin", "Bourn@example.com", "100010", true);
        List<Student> allStudents = studentsService.saveAll(Arrays.asList(s1, s2, s3, s4, s5, s6, s7, s8, s9, s10));

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
            project = projectsService.create(project);

            int tasksInProject = ThreadLocalRandom.current()
                    .nextInt(MIN_TASKS_IN_PROJECT, MAX_TASKS_IN_PROJECT + 1);
            for (int k = 0; k < tasksInProject; k++) {
                String taskName = lorem.getTitle(2, 4);
                String taskDescription = lorem.getWords(10, 20);
                TaskStatus taskStatus = TaskStatus.values()[ThreadLocalRandom.current().nextInt(TaskStatus.values().length)];

                Task t = new Task(taskName, k + 1, taskDescription, taskStatus);
                t.setProject(project);
                tasksService.create(t);
            }
        }

        log.info("Finished initializing data");
    }
}
