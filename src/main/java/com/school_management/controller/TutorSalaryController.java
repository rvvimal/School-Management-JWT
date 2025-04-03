package com.school_management.controller;


import com.school_management.dto.ResponseDTO;
import com.school_management.dto.TutorSalaryDTO;
import com.school_management.entity.TutorSalary;
import com.school_management.service.TutorSalaryService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class TutorSalaryController {
    @Autowired
    private TutorSalaryService tutorSalaryService;

    @PostMapping("/tutorSalary")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseDTO createTutorSalary(@Valid @RequestBody final TutorSalary tutorSalary) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.tutorSalaryService.createTutorSalary(tutorSalary));
    }

    @GetMapping("/tutorSalary")
    private ResponseDTO getAllTutorSalary() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorSalaryService.getAlltutorSalary());
    }

    @GetMapping("/tutorSalary/{id}")
    private ResponseDTO getTutorSalaryById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorSalaryService.findById(id));
    }

    @DeleteMapping("/tutorSalary/{id}")
    public ResponseDTO deleteTutorSalaryById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.tutorSalaryService.deleteById(id));
    }

    @PutMapping("/tutorSalary/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final TutorSalary tutorSalary) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.tutorSalaryService.updateById(tutorSalary, id));

    }

    @GetMapping("/tutorSalary/{id}/tutorAmount")
    public List<TutorSalaryDTO> amount(@PathVariable int id) {
        return this.tutorSalaryService.getFeeAmount(id);
    }
}
