package com.assignment.banking.BankingService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Builder
public class MortgageCheckResponse {
    private boolean feasible;
    private BigDecimal monthlyCost;
}
