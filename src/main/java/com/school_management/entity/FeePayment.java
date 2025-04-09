package com.school_management.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Table(name = "fee_payment")
@Entity
@Setter
@Getter
public class FeePayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date")
    private Date date;
    @Column(name = "fee_term")
    @Size(min = 3, message = "Size should be greater than four")
    private String fee_Term;
    @Column(name = "Amount")
    private double Amount;
    @Column(name = "status")
    @Size(min = 3, message = "Size should be greater than four")
    private String status;
    @ManyToOne()
    @JoinColumn(name = "student_id")
    private Student student;


}
