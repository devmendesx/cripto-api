package com.criptocloudapi.cloud.service;

import com.criptocloudapi.cloud.dto.response.FinancialDetailsDto;
import com.criptocloudapi.cloud.repository.ExpenseRepository;
import com.criptocloudapi.cloud.repository.RevenueRepository;
import com.criptocloudapi.cloud.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class FinancialService {

    private final RevenueRepository incomeRepository;
    private final ExpenseRepository chargeRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    public FinancialDetailsDto detailedFinancialByUser(String userCode) {
        userService.validateByCode(userCode);
        return null;

    }
}
