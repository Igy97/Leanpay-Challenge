package com.leanpaychallenge.leanpaychallenge.controller;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class LoanCalculatorControllerIT
{
    @Autowired
    private MockMvc mockMvc;

    @ParameterizedTest
    @MethodSource("loanRequestProvider")
    public void testCalculateLoan(int amount, double annualInterestRate, int numberOfMonths, String firstMonthPayment) throws Exception {
        String requestJson = String.format(
            "{ \"amount\": %d, \"annualInterestRate\": %.2f, \"numberOfMonths\": %d }",
            amount, annualInterestRate, numberOfMonths
        );

        mockMvc.perform(post("/api/v1/loanCalculator/calculate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestJson))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.['month 1']").value(firstMonthPayment)); // Change according to expected output
    }

    // MethodSource for 50 test cases with different input data
    private static Stream<Object[]> loanRequestProvider() {
        return Stream.of(
            new Object[]{10000, 5.0, 24, "438.71"},
            new Object[]{5000, 3.5, 12, "424.61"},
            new Object[]{15000, 4.5, 36, "446.20"},
            new Object[]{20000, 6.0, 48, "469.70"},
            new Object[]{3000, 5.0, 6, "507.32"},
            new Object[]{7500, 7.5, 18, "441.84"}
        );
    }

}
