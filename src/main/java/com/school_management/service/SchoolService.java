package com.school_management.service;

import com.school_management.entity.School;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.SchoolRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Transactional
    public School createSchool(final School school) {
        return this.schoolRepository.save(school);
    }

    public List<School> getAllSchool() {
        return this.schoolRepository.findAll();
    }

    public School findById(final int id) {
        return this.schoolRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.ID_DOES_NOT_EXIST));
    }


    public String deleteById(final int id) {
        if (this.schoolRepository.existsById(id)) {
            this.schoolRepository.deleteById(id);
            return Constant.REMOVE;
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
    }

    @Transactional
    public School updateById(final School school, final int id) {
        final School schools = this.schoolRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (school.getName() != null) {
            schools.setName(school.getName());
        }
        if (school.getAddress() != null) {
            schools.setAddress(school.getAddress());
        }
        if (school.getContact() != 0) {
            schools.setContact(school.getContact());
        }
        return this.schoolRepository.save(schools);
    }
}
