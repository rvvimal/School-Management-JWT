package com.school_management.dto;

import lombok.Data;

@Data
public class SignUpRequestDTO {
    private String userName;
    private String emailId;
    private String password;

}
