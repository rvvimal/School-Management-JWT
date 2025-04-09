package com.school_management.service;

import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.FeePayment;
import com.school_management.exception.UserNotFoundException;
import com.school_management.repository.FeePaymentRepository;
import com.school_management.util.Constant;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeePaymentService {
    @Autowired
    private FeePaymentRepository feePaymentRepository;

    @Transactional
    public FeePayment createFeePayment(final FeePayment feePayment) {
        return this.feePaymentRepository.save(feePayment);
    }

    public List<FeePayment> getAllFeePayment() {
        return this.feePaymentRepository.findAll();
    }

    public FeePayment findById(final int id) {
        return this.feePaymentRepository.findById(id).orElseThrow(() -> new RuntimeException(Constant.ID_DOES_NOT_EXIST));
    }

    public String deleteById(final int id) {
        if (this.feePaymentRepository.existsById(id)) {
            this.feePaymentRepository.deleteById(id);
        } else {
            throw new UserNotFoundException(Constant.NOT_FOUND + " " + id);
        }
        return Constant.REMOVE;
    }

    @Transactional
    public FeePayment updateById(final FeePayment feePayment, final int id) {
        final FeePayment feePayments = this.feePaymentRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(Constant.ID_DOES_NOT_EXIST));

        if (feePayment.getDate() != null) {
            feePayments.setDate(feePayment.getDate());
        }
        if (feePayment.getAmount() != 0) {
            feePayments.setAmount(feePayment.getAmount());
        }
        if (feePayment.getStatus() != null) {
            feePayments.setStatus(feePayment.getStatus());
        }
        if (feePayment.getFee_Term() != null) {
            feePayments.setFee_Term(feePayment.getFee_Term());
        }

        return this.feePaymentRepository.save(feePayments);
    }

    public List<SchoolFeeDetailsDTO> getfeepayment() {
        List<FeePayment> students = this.feePaymentRepository.findAllFeePayments();
        List<SchoolFeeDetailsDTO> schoolDetailsDTOS = new ArrayList<>();


        for (FeePayment payment : students) {
            int schoolId = payment.getStudent().getSchool().getId();
            String schoolName = payment.getStudent().getSchool().getName();
            double amount = payment.getAmount();

            SchoolFeeDetailsDTO existingSchoolDTO = null;
            for (SchoolFeeDetailsDTO schoolDTO : schoolDetailsDTOS) {
                if (schoolDTO.getSchoolId() == schoolId) {
                    existingSchoolDTO = schoolDTO;
                    break;
                }
            }
            if (existingSchoolDTO == null) {
                SchoolFeeDetailsDTO newSchoolDTO = new SchoolFeeDetailsDTO();
                newSchoolDTO.setSchoolId(schoolId);
                newSchoolDTO.setSchoolName(schoolName);
                newSchoolDTO.setMinimumFee(amount);
                newSchoolDTO.setMaximumFee(amount);
                schoolDetailsDTOS.add(newSchoolDTO);
            } else {

                if (amount < existingSchoolDTO.getMinimumFee()) {
                    existingSchoolDTO.setMinimumFee(amount);
                }
                if (amount > existingSchoolDTO.getMaximumFee()) {
                    existingSchoolDTO.setMaximumFee(amount);
                }
            }
        }

        return schoolDetailsDTOS;
    }

}