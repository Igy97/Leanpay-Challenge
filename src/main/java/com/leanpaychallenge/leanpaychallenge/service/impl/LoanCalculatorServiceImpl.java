package com.leanpaychallenge.leanpaychallenge.service.impl;

import com.leanpaychallenge.leanpaychallenge.service.LoanCalculatorService;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class LoanCalculatorServiceImpl implements LoanCalculatorService
{
    @Override
    public Map<String, String> calculateLoan(double amount, double annualInterestRate, int numberOfMonths) {
        // Convert annual interest rate to monthly interest rate in decimal form
        double monthlyInterestRate = annualInterestRate / 100 / 12;

        // Calculate monthly payment using the annuity formula
        double monthlyPayment = (amount * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, numberOfMonths)) /
            (Math.pow(1 + monthlyInterestRate, numberOfMonths) - 1);

        // Round the monthly payment to two decimal places
        double roundedPayment = Math.round(monthlyPayment * 100.0) / 100.0;

        Map<String, String> monthlyPayments = new LinkedHashMap<>(); // Use LinkedHashMap for ordered output

        // Generate monthly payment schedule with the fixed rounded payment amount for each month
        for (int month = 1; month <= numberOfMonths; month++) {
            monthlyPayments.put("month " + month, String.format("%.2f", roundedPayment));
        }

        return monthlyPayments;
    }
}
