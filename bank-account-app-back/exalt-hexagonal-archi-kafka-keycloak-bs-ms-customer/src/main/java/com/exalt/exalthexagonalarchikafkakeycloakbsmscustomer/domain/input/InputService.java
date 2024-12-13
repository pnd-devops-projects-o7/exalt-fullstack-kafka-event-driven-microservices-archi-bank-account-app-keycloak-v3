package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.input;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerResponseDto;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface InputService {
    Map<String, Map<String,String>> getWelcome();
    CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);
    List<CustomerResponseDto> loadAllPersistedCustomers();
    CustomerResponseDto getCustomerBy(String firstname, String lastname, String email);
    CustomerResponseDto archiveCustomer(UUID customerId);
    CustomerResponseDto getCustomerById(UUID customerId);

    CustomerResponseDto updateCustomerInfo(UUID customerId, CustomerRequestDto customerRequestDto);
}
