package com.leanpaychallenge.leanpaychallenge.controller;

import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;
import com.leanpaychallenge.leanpaychallenge.service.LoanCalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
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
        log.info("Received loan calculation request: {}", request);

        try {
            Map<String, String> response = loanCalculatorService.calculateAndSaveLoan(request);
            log.info("Calculated loan response: {}", response);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Unexpected error occurred: {}", e.getMessage(), e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", "An unexpected error occurred."));
        }
    }

}
