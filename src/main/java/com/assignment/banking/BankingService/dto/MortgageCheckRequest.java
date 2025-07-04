package com.assignment.banking.BankingService.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MortgageCheckRequest {
    @NotNull
    @Min(1)
    private BigDecimal income;

    @NotNull
    @Min(1)
    private int maturityPeriod;

    @NotNull
    @Min(1)
    private BigDecimal loanValue;

    @NotNull
    @Min(1)
    private BigDecimal homeValue;
}
