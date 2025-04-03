package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolDTO;
import com.school_management.dto.SchoolDetailsDTO;
import com.school_management.entity.StudentCourse;
import com.school_management.service.StudentCourseService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class StudentCourseController {

    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/studentCourse")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseDTO createStudentCourse(@Valid @RequestBody final StudentCourse studentCourse) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.studentCourseService.createStudentCourse(studentCourse));
    }

    @GetMapping("/studentCourse")
    private ResponseDTO getAllStudentCourse() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentCourseService.getAllStudentCourse());
    }

    @GetMapping("/studentCourse/{id}")
    private ResponseDTO getStudentCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentCourseService.findById(id));
    }

    @GetMapping("/studentCourse/retrieve-all-student")
    public ResponseDTO retrieveAllStudentCourseDetail() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.studentCourseService.retrieveAllStudentDetail());
    }

    @DeleteMapping("/studentCourse/{id}")
    public ResponseDTO deleteStudentCourseById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.studentCourseService.deleteById(id));
    }

    @GetMapping("/studentCourse/{schoolId}/page")
    public Page<SchoolDetailsDTO> schoolPages(@PathVariable int schoolId, @RequestParam final int pageIndex, @RequestParam final int pageSize) {
        return this.studentCourseService.getSchoolPages(schoolId, pageIndex, pageSize);
    }

    @GetMapping("/studentCourse/{id}/countCourse")
    public SchoolDTO courseCount(@PathVariable Integer id) {
        return this.studentCourseService.getCourseCount(id);
    }
}