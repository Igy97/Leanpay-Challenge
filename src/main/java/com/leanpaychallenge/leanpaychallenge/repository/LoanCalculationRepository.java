package com.leanpaychallenge.leanpaychallenge.repository;

import com.leanpaychallenge.leanpaychallenge.model.LoanCalculation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanCalculationRepository extends JpaRepository<LoanCalculation, Long>
{
}