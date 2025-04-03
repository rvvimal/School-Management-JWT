package com.school_management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SchoolFeeDetailsDTO {
    private int schoolId;
    private String schoolName;
    private Double minimumFee;
    private Double maximumFee;
}
