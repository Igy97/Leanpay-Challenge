package com.leanpaychallenge.leanpaychallenge.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanCalculatorControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testCalculateLoan() throws Exception {
        String requestJson = "{ \"amount\": 10000, \"annualInterestRate\": 5, \"numberOfMonths\": 24 }";

        mockMvc.perform(post("/api/v1/loanCalculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.['month 1']").value("438.71")); // Change according to expected output
    }

}
