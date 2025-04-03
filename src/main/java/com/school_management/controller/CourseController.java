package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Course;
import com.school_management.service.CourseService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseDTO createCourse(@Valid @RequestBody final Course course) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.courseService.createCourse(course));
    }

    @GetMapping("/course")
    private ResponseDTO getAllCourse() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.courseService.getAllCourse());
    }

    @GetMapping("/course/{id}")
    private ResponseDTO getCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.courseService.findById(id));
    }

    @DeleteMapping("/course/{id}")
    public ResponseDTO deleteCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.courseService.deleteById(id));
    }

    @PutMapping("/course/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final Course course) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.courseService.updateById(course, id));
    }
}
