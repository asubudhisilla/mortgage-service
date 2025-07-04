package service;

import com.assignment.banking.BankingService.dto.MortgageCheckRequest;
import com.assignment.banking.BankingService.dto.MortgageCheckResponse;
import com.assignment.banking.BankingService.service.MortgageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
@ActiveProfiles("test")
public class MortgageServiceTest {

    private MortgageService mortgageService;
    private MortgageCheckRequest request;
    @BeforeEach
    public void setup() {
        request = MortgageCheckRequest.builder().income(BigDecimal.valueOf(150000.00))
                .homeValue(BigDecimal.valueOf(450000.00))
                .loanValue(BigDecimal.valueOf(450000.00))
                .maturityPeriod(20).build();

        mortgageService = new MortgageService();

    }

    @Test
    public void givenValidRequest_ReturnTrueWithMonthlyAmount() {
        MortgageCheckResponse response = mortgageService.checkMortgage(request);
        assertThat(response).isNotNull();
        assertEquals(true, response.isFeasible());
    }

    @Test
    public void givenLoanValueExceeds4TimesIncomeRequest_ReturnFalseWithMonthlyAmountAsZero() {
        request = MortgageCheckRequest.builder().income(BigDecimal.valueOf(70000.00))
                .homeValue(BigDecimal.valueOf(450000.00))
                .loanValue(BigDecimal.valueOf(450000.00))
                .maturityPeriod(20).build();
        MortgageCheckResponse response = mortgageService.checkMortgage(request);
        assertThat(response).isNotNull();
        assertEquals(false, response.isFeasible());
        assertEquals(BigDecimal.ZERO, response.getMonthlyCost());
    }

    @Test
    public void givenLoanValueExceedsHomeValueRequest_ReturnFalseWithMonthlyAmountAsZero() {
        request = MortgageCheckRequest.builder().income(BigDecimal.valueOf(150000.00))
                .homeValue(BigDecimal.valueOf(400000.00))
                .loanValue(BigDecimal.valueOf(450000.00))
                .maturityPeriod(20).build();
        MortgageCheckResponse response = mortgageService.checkMortgage(request);
        assertThat(response).isNotNull();
        assertEquals(false, response.isFeasible());
        assertEquals(BigDecimal.ZERO, response.getMonthlyCost());
    }
}
