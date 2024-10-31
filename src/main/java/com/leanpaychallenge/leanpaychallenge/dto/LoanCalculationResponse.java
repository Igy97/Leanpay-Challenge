package com.leanpaychallenge.leanpaychallenge.dto;

import lombok.Data;

import java.util.Map;

@Data
public class LoanCalculationResponse {
    private Map<Integer, Double> monthlyPayments; // Month number and corresponding payment amount
}