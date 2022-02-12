package com.criptocloudapi.cloud.controller;


import com.criptocloudapi.cloud.dto.response.FinancialDetailsDto;
import com.criptocloudapi.cloud.service.FinancialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * @author Matheus Mendes
 * @version v1
 */

@RestController
@RequestMapping(path = "/api/v1/financials")
public class FinancialController {

    @Autowired
    private FinancialService financialService;

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<FinancialDetailsDto> userFinancialDetailed(@PathVariable(name = "user") String userCode){
        return ResponseEntity.ok(financialService.detailedFinancialByUser(userCode));
    }
}
