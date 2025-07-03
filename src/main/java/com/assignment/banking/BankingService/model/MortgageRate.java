package com.assignment.banking.BankingService.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class MortgageRate {
    private int maturityPeriod;
    private BigDecimal interestRate;
    private LocalDateTime lastUpdate;
}
