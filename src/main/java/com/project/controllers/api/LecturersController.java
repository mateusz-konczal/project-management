package com.project.controllers.api;

import com.project.model.Lecturer;
import com.project.services.LecturersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/api/lecturers")
@CrossOrigin
public class LecturersController {
    private final LecturersService lecturersService;

    @Autowired
    public LecturersController(LecturersService lecturersService) {
        this.lecturersService = lecturersService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Lecturer>> findAllLecturers() {
        List<Lecturer> allLecturers = lecturersService.findAll();
        return new ResponseEntity<>(allLecturers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lecturer> findById(@PathVariable("id") long id) {
        Optional<Lecturer> lecturer = lecturersService.findById(id);

        if (lecturer.isPresent()) {
            return new ResponseEntity<>(lecturer.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public ResponseEntity<Lecturer> create(@RequestBody Lecturer lecturer) {
        lecturer = lecturersService.create(lecturer);

        if (lecturer != null) {
            return new ResponseEntity<>(lecturer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping()
    public ResponseEntity<Lecturer> update(@RequestBody Lecturer lecturer) {
        lecturer = lecturersService.update(lecturer);

        if (lecturer != null) {
            return new ResponseEntity<>(lecturer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") long id) {
        lecturersService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
