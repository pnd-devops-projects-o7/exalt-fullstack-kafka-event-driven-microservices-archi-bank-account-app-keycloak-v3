package com.exalt.exalt_hexagonal_archi_kafka_keycloak_bs_ms_bank_account.infra.input.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/bank-account")
public class BankAccountApiWeb {
    @Value("${spring.application.name}")
    private String apiName;
    @GetMapping
    public ResponseEntity<Map<String, String>> getApplicationName(){
        return ResponseEntity.ok(Map.of("application-name",apiName));
    }
}
