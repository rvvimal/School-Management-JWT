package com.school_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolDetailsDTO {
    private int id;
    private String schoolName;
    private int tutorId;
    private String tutorName;
    private int studentId;
    private String studentFirstName;
    private String studentLastName;
    private String enrollmentDate;
    private int courseId;
    private String courseName;
}
