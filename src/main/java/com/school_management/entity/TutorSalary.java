package com.school_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "tutor_salary")
public class TutorSalary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "month")
    private String month;
    @Size(min = 4, message = "Size should be greater than four")
    @Column(name = "paid")
    private String paid;
    @Column(name = "amount")
    private double amount;
    @ManyToOne()
    @JoinColumn(name = "tutor_id")
    private Tutor tutor;
}
