package com.school_management.controller;

import com.school_management.dto.ResponseDTO;
import com.school_management.dto.SchoolFeeDetailsDTO;
import com.school_management.entity.FeePayment;
import com.school_management.service.FeePaymentService;
import com.school_management.util.Constant;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class FeePaymentController {
    @Autowired
    private FeePaymentService feePaymentService;

    @PostMapping("/feePayment")
    @ResponseStatus(HttpStatus.CREATED)
    private ResponseDTO createFeePayment(@Valid @RequestBody final FeePayment feePayment) {
        return new ResponseDTO(HttpStatus.CREATED.value(), Constant.CREATE, this.feePaymentService.createFeePayment(feePayment));
    }

    @GetMapping("/feePayment")
    private ResponseDTO getAllFeePayment() {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.feePaymentService.getAllFeePayment());
    }

    @GetMapping("/feePayment/{id}")
    private ResponseDTO getFeePaymentById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.RETRIEVE, this.feePaymentService.findById(id));
    }

    @DeleteMapping("/feePayment/{id}")
    public ResponseDTO deleteFeePaymentById(@PathVariable final int id) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.DELETE, this.feePaymentService.deleteById(id));
    }

    @PutMapping("/feePayment/{id}")
    public ResponseDTO updateById(@PathVariable final int id, @Valid @RequestBody final FeePayment feePayment) {
        return new ResponseDTO(HttpStatus.OK.value(), Constant.UPDATE, this.feePaymentService.updateById(feePayment, id));
    }

    @GetMapping("/feePayment/feeDetails")
    public List<SchoolFeeDetailsDTO> feePayment() {
        return this.feePaymentService.getfeepayment();
    }
}
