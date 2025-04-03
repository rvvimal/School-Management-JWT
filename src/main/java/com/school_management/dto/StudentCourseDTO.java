package com.school_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDTO {
    private String tutorName;
    private String schoolName;
    private String studentName;
    private String courseName;
}
