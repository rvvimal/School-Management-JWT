package com.school_management.service;

import com.school_management.entity.Course;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.CourseRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Transactional
    public Course createCourse(final Course course) {
        return this.courseRepository.save(course);
    }

    public List<Course> getAllCourse() {
        return this.courseRepository.findAll();
    }

    public Course findById(final int id) {
        return this.courseRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.ID_DOES_NOT_EXIST));
    }


    public String deleteById(final int id) {
        if (this.courseRepository.existsById(id)) {
            this.courseRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
        return Constant.REMOVE;
    }

    @Transactional
    public Course updateById(final Course course, final int id) {
        final Course courses = this.courseRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));
        if (course.getName() != null) {
            courses.setName(course.getName());
        }
        return this.courseRepository.save(courses);
    }
}

