package com.school_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TutorSalaryDTO {
    private int tutorId;
    private String tutorName;
    private Double amount;
    private int schoolId;
}
