package com.leanpaychallenge.leanpaychallenge.controller;

import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;
import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/loanCalculator")
public class LoanCalculatorController
{
    @PostMapping("/calculate")
    public ResponseEntity<Map<String, String>> calculateLoan(@RequestBody LoanCalculationRequest request) {
        // Convert annual interest rate to monthly interest rate in decimal form
        double monthlyInterestRate = request.getAnnualInterestRate() / 100 / 12;

        // Calculate monthly payment using the annuity formula
        double monthlyPayment = (request.getAmount() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, request.getNumberOfMonths())) /
            (Math.pow(1 + monthlyInterestRate, request.getNumberOfMonths()) - 1);

        // Round the monthly payment to two decimal places
        double roundedPayment = Math.round(monthlyPayment * 100.0) / 100.0;

        Map<String, String> monthlyPayments = new LinkedHashMap<>(); // Use LinkedHashMap for ordered output

        // Generate monthly payment schedule with the fixed rounded payment amount for each month
        for (int month = 1; month <= request.getNumberOfMonths(); month++) {
            monthlyPayments.put("month " + month, String.format("%.2f", roundedPayment));
        }

        return ResponseEntity.ok(monthlyPayments);
    }

}
