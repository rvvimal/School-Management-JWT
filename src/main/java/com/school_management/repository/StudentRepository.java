package com.school_management.repository;

import com.school_management.dto.StudentDetailsDTO;
import com.school_management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    @Query(value = "SELECT s.id AS studentId, " +
            "CONCAT(s.first_name, ' ', s.last_name) AS studentName, " +
            "GROUP_CONCAT(c.name SEPARATOR ', ') AS courseNames " +
            "FROM student s " +
            "JOIN student_course sc ON s.id = sc.student_id " +
            "JOIN course c ON sc.course_id = c.id " +
            "WHERE s.id = :id " +
            "GROUP BY s.id", nativeQuery = true)
    List<StudentDetailsDTO> findStudentWithCourses(@Param("id") int id);

}

