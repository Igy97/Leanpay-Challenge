package com.leanpaychallenge.leanpaychallenge.repository;

import com.leanpaychallenge.leanpaychallenge.model.MonthlyPayment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyPaymentRepository extends JpaRepository<MonthlyPayment, Long>
{
}

