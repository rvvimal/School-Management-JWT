package com.school_management.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationResponse {
    private int totalPages;
    private long totalElements;
    private int pageSize;
    private Object details;
}