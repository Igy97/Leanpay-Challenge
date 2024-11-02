package com.leanpaychallenge.leanpaychallenge.controller;

import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;
import com.leanpaychallenge.leanpaychallenge.service.LoanCalculatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/loanCalculator")
public class LoanCalculatorController
{
    private final LoanCalculatorService loanCalculatorService;

    @Autowired
    public LoanCalculatorController(LoanCalculatorService loanCalculatorService) {
        this.loanCalculatorService = loanCalculatorService;
    }

    @PostMapping("/calculate")
    public ResponseEntity<Map<String, String>> calculateLoan(@RequestBody LoanCalculationRequest request) {
        Map<String, String> response = loanCalculatorService.calculateAndSaveLoan(request);
        return ResponseEntity.ok(response);
    }

}
