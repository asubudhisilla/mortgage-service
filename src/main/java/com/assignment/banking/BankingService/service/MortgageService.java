package com.assignment.banking.BankingService.service;

import com.assignment.banking.BankingService.dto.MortgageCheckRequest;
import com.assignment.banking.BankingService.dto.MortgageCheckResponse;
import com.assignment.banking.BankingService.model.MortgageRate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class MortgageService {

    private final List<MortgageRate> mortgageRates = new ArrayList<>();

    public MortgageService(){
        //initialize in-memory mortgage rates.
        mortgageRates.add(new MortgageRate(10, 3.5, LocalDateTime.now()));
        mortgageRates.add(new MortgageRate(15, 4, LocalDateTime.now()));
        mortgageRates.add(new MortgageRate(20, 3.92, LocalDateTime.now()));
    }

    public List<MortgageRate> getMortgageRates()
    {
        return mortgageRates;
    }

    public MortgageCheckResponse checkMortgage(MortgageCheckRequest request){
        //Business rules
        if(request.getLoanValue().compareTo(request.getIncome().multiply(BigDecimal.valueOf(4))) > 0 ||
           request.getLoanValue().compareTo(request.getHomeValue())  > 0){
            return new MortgageCheckResponse(false, BigDecimal.ZERO);
        }

        //Find interest rate for the given maturity period
        MortgageRate rate = mortgageRates.stream()
                .filter(r -> r.getMaturityPeriod() == request.getMaturityPeriod())
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Maturity period"));

        //Calculate monthly cost
        double monthlyInterestRate = rate.getInterestRate() / 100 / 12;
        int numberOfPayments = rate.getMaturityPeriod() * 12;
        double ratio = (monthlyInterestRate * (Math.pow((1 + monthlyInterestRate), numberOfPayments))) /
                        ((Math.pow((1 + monthlyInterestRate), numberOfPayments)) - 1);

        BigDecimal repayments = request.getLoanValue().multiply(BigDecimal.valueOf(ratio));

        return new MortgageCheckResponse(true, repayments.setScale(2,RoundingMode.DOWN));
    }


}
