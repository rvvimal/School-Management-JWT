package com.school_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "student")

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "email")
    private String email;
    @Column(name = "contact_number")
    private long contactNumber;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "enrollment_date")
    private String enrollmentDate;

    @ManyToOne()
    @JoinColumn(name = "school_id")
    private School school;
}
