package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.TutorCourse;
import com.school_management.service.TutorCourseService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TutorCourseController {
    @Autowired
    private TutorCourseService tutorCourseService;

    @PostMapping("/tutorCourse")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseDTO createTutorCourse(@Valid @RequestBody final TutorCourse tutorCourse) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.tutorCourseService.createTutorCourse(tutorCourse));
    }

    @GetMapping("/tutorCourse")
    private ResponseDTO getAllTutorCourse() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorCourseService.getAlltutorCourse());
    }

    @GetMapping("/tutorCourse/{id}")
    private ResponseDTO getTutorCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorCourseService.findById(id));
    }

    @DeleteMapping("/tutorCourse/{id}")
    public ResponseDTO deleteTutorCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.tutorCourseService.deleteById(id));
    }

}