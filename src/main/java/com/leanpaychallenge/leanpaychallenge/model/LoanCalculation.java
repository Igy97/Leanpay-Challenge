package com.leanpaychallenge.leanpaychallenge.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "loan_calculation")
public class LoanCalculation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private double amount;

    @Column(name = "annual_interest_rate", nullable = false)
    private double annualInterestRate;

    @Column(name = "number_of_months", nullable = false)
    private int numberOfMonths;

    @Column(name = "calculation_date", nullable = false)
    private LocalDateTime calculationDate = LocalDateTime.now();

    @Column(name = "total_payment", nullable = false)
    private double totalPayment;

    @OneToMany(mappedBy = "loanCalculation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<MonthlyPayment> monthlyPayments;
}
