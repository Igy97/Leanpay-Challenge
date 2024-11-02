package com.leanpaychallenge.leanpaychallenge.service.impl;

import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;
import com.leanpaychallenge.leanpaychallenge.model.LoanCalculation;
import com.leanpaychallenge.leanpaychallenge.model.MonthlyPayment;
import com.leanpaychallenge.leanpaychallenge.repository.LoanCalculationRepository;
import com.leanpaychallenge.leanpaychallenge.repository.MonthlyPaymentRepository;
import com.leanpaychallenge.leanpaychallenge.service.LoanCalculatorService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class LoanCalculatorServiceImpl implements LoanCalculatorService {

    private final LoanCalculationRepository loanCalculationRepository;
    private final MonthlyPaymentRepository monthlyPaymentRepository;

    @Autowired
    public LoanCalculatorServiceImpl(LoanCalculationRepository loanCalculationRepository, MonthlyPaymentRepository monthlyPaymentRepository) {
        this.loanCalculationRepository = loanCalculationRepository;
        this.monthlyPaymentRepository = monthlyPaymentRepository;
    }

    @Transactional
    @Override
    public Map<String, String> calculateAndSaveLoan(LoanCalculationRequest request) {
        // Check if a loan with the same parameters already exists
        LoanCalculation existingLoan = loanCalculationRepository.findWithPayments(request.getAmount(), request.getAnnualInterestRate(), request.getNumberOfMonths());

        if (existingLoan != null) {
            // Return existing monthly payments
            Map<String, String> monthlyPayments = new LinkedHashMap<>();
            existingLoan.getMonthlyPayments().forEach(payment ->
                monthlyPayments.put("month " + payment.getMonthNumber(), String.format("%.2f", payment.getPaymentAmount()))
            );
            return monthlyPayments;
        }

        // Calculate monthly payment
        double monthlyInterestRate = request.getAnnualInterestRate() / 100 / 12;
        double monthlyPayment = (request.getAmount() * monthlyInterestRate * Math.pow(1 + monthlyInterestRate, request.getNumberOfMonths())) /
            (Math.pow(1 + monthlyInterestRate, request.getNumberOfMonths()) - 1);
        double roundedPayment = Math.round(monthlyPayment * 100.0) / 100.0;

        // Create a new LoanCalculation entity
        LoanCalculation loanCalculation = new LoanCalculation();
        loanCalculation.setAmount(request.getAmount());
        loanCalculation.setAnnualInterestRate(request.getAnnualInterestRate());
        loanCalculation.setNumberOfMonths(request.getNumberOfMonths());
        loanCalculation.setTotalPayment(roundedPayment * request.getNumberOfMonths());

        loanCalculation = loanCalculationRepository.save(loanCalculation); // Save LoanCalculation

        Map<String, String> monthlyPayments = new LinkedHashMap<>();
        for (int month = 1; month <= request.getNumberOfMonths(); month++) {
            monthlyPayments.put("month " + month, String.format("%.2f", roundedPayment));

            // Save each monthly payment
            MonthlyPayment payment = new MonthlyPayment();
            payment.setLoanCalculation(loanCalculation);
            payment.setMonthNumber(month);
            payment.setPaymentAmount(roundedPayment);

            monthlyPaymentRepository.save(payment); // Save MonthlyPayment
        }

        return monthlyPayments;
    }
}
