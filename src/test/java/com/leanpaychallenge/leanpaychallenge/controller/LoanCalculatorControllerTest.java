package com.leanpaychallenge.leanpaychallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leanpaychallenge.leanpaychallenge.dto.LoanCalculationRequest;
import com.leanpaychallenge.leanpaychallenge.service.LoanCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class LoanCalculatorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LoanCalculatorService loanCalculatorService;

    @InjectMocks
    private LoanCalculatorController loanCalculatorController;

    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(loanCalculatorController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void testCalculateLoanUnexpectedError() throws Exception {
        // Given
        LoanCalculationRequest request = new LoanCalculationRequest(10000, 5, 24);

        // Simulate an unexpected error in the service layer
        doThrow(new RuntimeException("Unexpected error")).when(loanCalculatorService).calculateAndSaveLoan(any());

        // When & Then
        mockMvc.perform(post("/api/v1/loanCalculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isInternalServerError())
            .andExpect(jsonPath("$.error").value("An unexpected error occurred."));
    }
}
