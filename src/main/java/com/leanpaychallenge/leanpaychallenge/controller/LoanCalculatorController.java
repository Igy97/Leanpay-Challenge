package com.leanpaychallenge.leanpaychallenge.controller;

import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;
import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loanCalculator")
public class LoanCalculatorController
{
    @PostMapping("/calculate")
    public ResponseEntity<Map<String, Double>> calculateLoan(@RequestBody LoanCalculationRequest request) {
        double monthlyInterestRate = request.getAnnualInterestRate() / 100 / 12; // Convert annual percentage to monthly rate
        double monthlyPayment = (request.getAmount() * monthlyInterestRate) /
            (1 - Math.pow(1 + monthlyInterestRate, -request.getNumberOfMonths()));

        Map<String, Double> monthlyPayments = new HashMap<>();

        // Generate monthly payment schedule
        for (int month = 1; month <= request.getNumberOfMonths(); month++) {
            monthlyPayments.put("month " + month, monthlyPayment);
        }

        return ResponseEntity.ok(monthlyPayments);
    }

}
