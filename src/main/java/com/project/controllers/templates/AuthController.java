package com.project.controllers.templates;

import com.project.model.Lecturer;
import com.project.model.Student;
import com.project.model.UserRole;
import com.project.services.LecturersService;
import com.project.services.StudentsService;
import com.project.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class AuthController {

    private final UsersService usersService;
    private final StudentsService studentsService;
    private final LecturersService lecturersService;

    @Autowired
    public AuthController(UsersService usersService, StudentsService studentsService,
                          LecturersService lecturersService) {
        this.usersService = usersService;
        this.studentsService = studentsService;
        this.lecturersService = lecturersService;
    }

    @GetMapping("/")
    public String getIndex() {
        return "index";
    }

    @GetMapping("/login")
    public String getLogin() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String getSignup() {
        return "auth/signup";
    }

    @GetMapping("/signup/student")
    public String getStudentSignup(Model model) {
        model.addAttribute("student", new Student());
        return "auth/student-signup";
    }

    @PostMapping("/signup/student")
    public String postStudentSignup(@ModelAttribute @Valid Student student, Errors errors) {
        if (usersService.existsByUsername(student.getUsername())) {
            errors.rejectValue("username", "username.exists", "Username already exists.");
            return "auth/student-signup";
        }
        if (usersService.existsByEmail(student.getEmail())) {
            errors.rejectValue("email", "email.exists", "Email already exists.");
            return "auth/student-signup";
        }
        if (!usersService.validatePassword(student.getPassword())) {
            errors.rejectValue("password", "password.weak",
                    "Password should contain at least one digit, one lowercase and one uppercase.");
            return "auth/student-signup";
        }

        student.setUserRole(UserRole.STUDENT);
        studentsService.create(student);
        return "redirect:/login";
    }

    @GetMapping("/signup/lecturer")
    public String getLecturerSignup(Model model) {
        model.addAttribute("lecturer", new Lecturer());
        return "auth/lecturer-signup";
    }

    @PostMapping("/signup/lecturer")
    public String postLecturerSignup(@ModelAttribute @Valid Lecturer lecturer, Errors errors) {
        if (usersService.existsByUsername(lecturer.getUsername())) {
            errors.rejectValue("username", "username.exists", "Username already exists.");
            return "auth/lecturer-signup";
        }
        if (usersService.existsByEmail(lecturer.getEmail())) {
            errors.rejectValue("email", "email.exists", "Email already exists.");
            return "auth/lecturer-signup";
        }
        if (!usersService.validatePassword(lecturer.getPassword())) {
            errors.rejectValue("password", "password.weak",
                    "Password should contain at least one digit, one lowercase and one uppercase.");
            return "auth/lecturer-signup";
        }

        lecturer.setUserRole(UserRole.LECTURER);
        lecturersService.create(lecturer);
        return "redirect:/login";
    }

}
