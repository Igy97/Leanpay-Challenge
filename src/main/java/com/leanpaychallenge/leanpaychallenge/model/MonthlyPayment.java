package com.leanpaychallenge.leanpaychallenge.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "monthly_payment")
public class MonthlyPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "loan_calculation_id", nullable = false)
    private LoanCalculation loanCalculation;

    @Column(name = "month_number", nullable = false)
    private int monthNumber;

    @Column(name = "payment_amount", nullable = false)
    private double paymentAmount;
}
