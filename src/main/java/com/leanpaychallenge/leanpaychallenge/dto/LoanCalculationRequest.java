package com.leanpaychallenge.leanpaychallenge.dto;


import lombok.Data;

@Data
public class LoanCalculationRequest {
    private double amount; // Loan amount
    private double annualInterestRate; // Annual interest rate in percentage
    private int numberOfMonths; // Number of months for the loan

    public LoanCalculationRequest(double amount, double annualInterestRate, int numberOfMonths)
    {
        this.amount = amount;
        this.annualInterestRate = annualInterestRate;
        this.numberOfMonths = numberOfMonths;
    }
}
