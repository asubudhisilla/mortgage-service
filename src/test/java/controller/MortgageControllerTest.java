package controller;

import com.assignment.banking.BankingService.controller.MortgageController;
import com.assignment.banking.BankingService.dto.MortgageCheckRequest;
import com.assignment.banking.BankingService.dto.MortgageCheckResponse;
import com.assignment.banking.BankingService.service.MortgageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class MortgageControllerTest {

    @Mock
    private MortgageService mortgageService;

    @InjectMocks
    private MortgageController mortgageController;

    @Test
    void testCheckMortgageWithValidInput(){
        MortgageCheckRequest request = MortgageCheckRequest.builder().income(BigDecimal.valueOf(150000.00))
                .homeValue(BigDecimal.valueOf(450000.00))
                .loanValue(BigDecimal.valueOf(450000.00))
                .maturityPeriod(20).build();
        MortgageCheckResponse response = MortgageCheckResponse.builder()
                        .monthlyCost(BigDecimal.valueOf(2250.00))
                                .feasible(true).build();
        when(mortgageService.checkMortgage(request)).thenReturn(response);
        ResponseEntity<MortgageCheckResponse> httpResponse = mortgageController.checkMortgage(request);
        MortgageCheckResponse apiResponse = httpResponse.getBody();
        assertNotNull(httpResponse);
        assertNotNull(apiResponse);
        assertEquals(true, apiResponse.isFeasible());
        assertEquals(BigDecimal.valueOf(2250.00), apiResponse.getMonthlyCost());
    }
}
