package com.assignment.banking.BankingService.controller;

import com.assignment.banking.BankingService.dto.MortgageCheckRequest;
import com.assignment.banking.BankingService.dto.MortgageCheckResponse;
import com.assignment.banking.BankingService.model.MortgageRate;
import com.assignment.banking.BankingService.service.MortgageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MortgageController {

    private final MortgageService mortgageService;

    @GetMapping("/interest-rates")
    public ResponseEntity<List<MortgageRate>> getInterestRates(){
        return ResponseEntity.ok(mortgageService.getMortgageRates());
    }

    @PostMapping("/mortgage-check")
    public ResponseEntity<MortgageCheckResponse> checkMortgage(@Valid @RequestBody MortgageCheckRequest request)
    {
        return ResponseEntity.ok(mortgageService.checkMortgage(request));
    }
}

