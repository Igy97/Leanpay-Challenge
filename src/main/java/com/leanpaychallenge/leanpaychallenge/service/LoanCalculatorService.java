package com.leanpaychallenge.leanpaychallenge.service;

import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;

import java.util.Map;

public interface LoanCalculatorService
{
        Map<String, String> calculateAndSaveLoan(LoanCalculationRequest request);
}
