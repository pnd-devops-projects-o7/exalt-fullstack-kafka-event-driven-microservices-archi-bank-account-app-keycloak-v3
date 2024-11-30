package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/customer")
@RequiredArgsConstructor
public class CustomerApiWeb {
    @Value("${spring.application.name}")
    private String apiName;
    private final InputService inputService;

    @GetMapping
    public ResponseEntity<Map<String, String>> getApplicationName(){
        return ResponseEntity.ok(Map.of("application-name",apiName));
    }
    @PostMapping("/customers")
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(inputService.createCustomer(customerRequestDto));
    }
    @GetMapping("/customers")
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(){
        return ResponseEntity.ok().body(inputService.loadAllPersistedCustomers());
    }
    @GetMapping(value = "/customers/{customerId}")
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable(name = "customerId") UUID customerId){
        return ResponseEntity.ok().body(inputService.getCustomerById(customerId));
    }
    @GetMapping(value = "/customers/{firstname}/{lastname}/{email}")
    public ResponseEntity<CustomerResponseDto> getCustomerBy(@PathVariable(name = "firstname") final String firstname,
                                                             @PathVariable(name = "lastname")  final String lastname,
                                                             @PathVariable(name = "email") final String email){
       return ResponseEntity.ok().body(inputService.getCustomerBy(firstname,lastname, email));
    }
    @PostMapping(value = "/customers/archive/{customerId}")
    public ResponseEntity<CustomerResponseDto> archiveCustomer(@PathVariable(name = "customerId")UUID customerId){
        return ResponseEntity.ok().body(inputService.archiveCustomer(customerId));
    }
    @PutMapping(value = "/customers/update/{customerId}")
    public ResponseEntity<CustomerResponseDto> updateCustomerInfo(@PathVariable(name = "customerId") UUID customerId,
                                                                  @RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok().body(inputService.updateCustomerInfo(customerId, customerRequestDto));
    }

}
