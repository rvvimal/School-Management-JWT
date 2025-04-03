package com.school_management.repository;

import com.school_management.entity.FeePayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeePaymentRepository extends JpaRepository<FeePayment, Integer> {

    @Query("SELECT f FROM FeePayment f")
    List<FeePayment> findAllFeePayments();
}

