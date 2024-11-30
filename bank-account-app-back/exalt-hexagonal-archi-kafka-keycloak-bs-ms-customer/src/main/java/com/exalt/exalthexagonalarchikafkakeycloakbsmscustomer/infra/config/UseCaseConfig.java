package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.config;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.output.CustomerProducerEvent;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.output.CustomerOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.usecase.InputServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UseCaseConfig {
    @Bean
    public InputServiceImpl config (CustomerProducerEvent customerProducerEvent, CustomerOutputService outputService){
        return new InputServiceImpl(customerProducerEvent, outputService);
    }
}
