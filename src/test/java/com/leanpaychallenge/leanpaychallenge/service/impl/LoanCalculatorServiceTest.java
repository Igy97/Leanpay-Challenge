package com.leanpaychallenge.leanpaychallenge.service.impl;

import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;
import com.leanpaychallenge.leanpaychallenge.model.LoanCalculation;
import com.leanpaychallenge.leanpaychallenge.model.MonthlyPayment;
import com.leanpaychallenge.leanpaychallenge.repository.LoanCalculationRepository;
import com.leanpaychallenge.leanpaychallenge.repository.MonthlyPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;


public class LoanCalculatorServiceTest {

    @InjectMocks
    private LoanCalculatorServiceImpl loanCalculatorService;

    @Mock
    private LoanCalculationRepository loanCalculationRepository;

    @Mock
    private MonthlyPaymentRepository monthlyPaymentRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCalculateAndSaveLoan() {
        // Arrange
        LoanCalculationRequest request = new LoanCalculationRequest(10000, 5, 24);
        LoanCalculation loanCalculation = new LoanCalculation();
        loanCalculation.setId(1L); // Set an ID or other relevant fields if necessary
        when(loanCalculationRepository.save(any(LoanCalculation.class))).thenReturn(loanCalculation);

        // Mock behavior for monthly payments
        when(monthlyPaymentRepository.save(any(MonthlyPayment.class))).thenReturn(new MonthlyPayment());

        // Act
        Map<String, String> result = loanCalculatorService.calculateAndSaveLoan(request);

        // Assert
        assertNotNull(result);
        assertEquals(24, result.size()); // Assuming there are 24 monthly payments
    }
}