package com.school_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "name")
    private String name;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "email")
    private String email;
    @Column(name = "contact_number")
    private long contactNumber;
    @ManyToOne()
    @JoinColumn(name = "school_id")
    private School school;
}
