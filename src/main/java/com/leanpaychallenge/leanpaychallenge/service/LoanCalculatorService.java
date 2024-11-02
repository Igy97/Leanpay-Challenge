package com.leanpaychallenge.leanpaychallenge.service;

import java.util.Map;

public interface LoanCalculatorService
{
        Map<String, String> calculateLoan(double amount, double annualInterestRate, int numberOfMonths);
}
