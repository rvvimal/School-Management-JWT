package com.school_management.service;


import com.school_management.entity.TutorCourse;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorCourseRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class TutorCourseService {
    @Autowired
    private TutorCourseRepository tutorCourseRepository;

    @Transactional
    public TutorCourse createTutorCourse(final TutorCourse tutorCourse) {
        return this.tutorCourseRepository.save(tutorCourse);
    }

    public List<TutorCourse> getAlltutorCourse() {
        return this.tutorCourseRepository.findAll();
    }

    public TutorCourse findById(final int id) {
        return this.tutorCourseRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.ID_DOES_NOT_EXIST));
    }


    public String deleteById(final int id) {
        if (this.tutorCourseRepository.existsById(id)) {
            this.tutorCourseRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
        return Constant.REMOVE;
    }


}