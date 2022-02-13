package com.cryptocloudapi.cloud.dto.response;


import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FinancialDetailsDto {
    private BigDecimal expenseBalance;
    private BigDecimal revenueBalance;
    private BigDecimal balance;
}
