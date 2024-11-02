package com.leanpaychallenge.leanpaychallenge.repository;

import com.leanpaychallenge.leanpaychallenge.model.LoanCalculation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoanCalculationRepository extends JpaRepository<LoanCalculation, Long>
{

    @Query("SELECT lc FROM LoanCalculation lc LEFT JOIN FETCH lc.monthlyPayments " +
        "WHERE lc.amount = :amount AND lc.annualInterestRate = :annualInterestRate AND lc.numberOfMonths = :numberOfMonths")
    LoanCalculation findWithPayments(double amount, double annualInterestRate, int numberOfMonths);
}