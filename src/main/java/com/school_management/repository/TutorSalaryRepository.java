package com.school_management.repository;

import com.school_management.entity.TutorSalary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutorSalaryRepository extends JpaRepository<TutorSalary, Integer> {

    List<TutorSalary> findByTutor_School_Id(int id);
}