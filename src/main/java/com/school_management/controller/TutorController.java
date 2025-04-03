package com.school_management.controller;


import com.school_management.dto.ResponseDTO;
import com.school_management.entity.Tutor;
import com.school_management.service.TutorService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class TutorController {
    @Autowired
    private TutorService tutorService;

    @PostMapping("/tutor")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseDTO createTutor(@Valid @RequestBody final Tutor tutor) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.tutorService.createTutor(tutor));
    }

    @GetMapping("/tutor")
    private ResponseDTO getAllTutor() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorService.getAlltutor());
    }

    @GetMapping("/tutor/{id}")
    private ResponseDTO getTutorById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.tutorService.findById(id));
    }

    @DeleteMapping("/tutor/{id}")
    public ResponseDTO deleteTutorById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.tutorService.deleteById(id));
    }

    @PutMapping("/tutor/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final Tutor tutor) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.tutorService.updateById(tutor, id));

    }
}

