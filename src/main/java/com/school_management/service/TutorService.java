package com.school_management.service;

import com.school_management.entity.Tutor;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.TutorRepository;
import com.school_management.util.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository tutorRepository;

    @Transactional
    public Tutor createTutor(final Tutor tutor) {
        return this.tutorRepository.save(tutor);
    }

    public List<Tutor> getAlltutor() {
        return this.tutorRepository.findAll();
    }

    public Tutor findById(final int id) {
        return this.tutorRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.ID_DOES_NOT_EXIST));
    }

    public String deleteById(final int id) {
        if (this.tutorRepository.existsById(id)) {
            this.tutorRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
        return Constant.REMOVE;
    }

    @Transactional
    public Tutor updateById(final Tutor tutor, final int id) {
        final Tutor tutors = this.tutorRepository.findById(id).orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (tutor.getName() != null) {
            tutors.setName(tutor.getName());
        }
        if (tutor.getEmail() != null) {
            tutors.setEmail(tutor.getEmail());
        }
        if (tutor.getContactNumber() != 0) {
            tutors.setContactNumber(tutor.getContactNumber());
        }
        return this.tutorRepository.save(tutors);
    }
}

