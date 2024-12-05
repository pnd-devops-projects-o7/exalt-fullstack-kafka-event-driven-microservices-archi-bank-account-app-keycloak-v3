package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.input.web;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.input.InputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerRequestDto;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.dto.CustomerResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/customer")
@RequiredArgsConstructor
public class CustomerApiWeb {
    private final InputService inputService;

    @GetMapping
    //accessible to any user
    @Secured({"client_user","client_admin","client_owner"})
    public ResponseEntity<Map<String, Map<String,String>>> welcome(){
        return ResponseEntity.ok().body(inputService.getWelcome());
    }
    @PostMapping("/customers")
    // only admin and owner can call this api
    @Secured({"client_admin","client_owner"})
    public ResponseEntity<CustomerResponseDto> createCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(inputService.createCustomer(customerRequestDto));
    }
    @GetMapping("/customers")
    // user and admin can call this api
    @Secured({"client_admin","client_owner"})
    public ResponseEntity<List<CustomerResponseDto>> getAllCustomers(){
        return ResponseEntity.ok().body(inputService.loadAllPersistedCustomers());
    }
    @GetMapping(value = "/customers/{customerId}")
    @Secured({"client_admin","client_owner"})
    public ResponseEntity<CustomerResponseDto> getCustomerById(@PathVariable(name = "customerId") UUID customerId){
        return ResponseEntity.ok().body(inputService.getCustomerById(customerId));
    }
    @GetMapping(value = "/customers/{firstname}/{lastname}/{email}")
    @Secured({"client_admin","client_owner"})
    // admin and user can call this api
    public ResponseEntity<CustomerResponseDto> getCustomerBy(@PathVariable(name = "firstname") final String firstname,
                                                             @PathVariable(name = "lastname")  final String lastname,
                                                             @PathVariable(name = "email") final String email){
       return ResponseEntity.ok().body(inputService.getCustomerBy(firstname,lastname, email));
    }
    @PostMapping(value = "/customers/archive/{customerId}")
    // only admin can call this api
    @Secured({"client_admin","client_owner"})
    public ResponseEntity<CustomerResponseDto> archiveCustomer(@PathVariable(name = "customerId")UUID customerId){
        return ResponseEntity.ok().body(inputService.archiveCustomer(customerId));
    }
    @PutMapping(value = "/customers/update/{customerId}")
    // only owner can call this api
    @PreAuthorize("hasRole('client_owner')")
    public ResponseEntity<CustomerResponseDto> updateCustomerInfo(@PathVariable(name = "customerId") UUID customerId,
                                                                  @RequestBody CustomerRequestDto customerRequestDto){
        return ResponseEntity.ok().body(inputService.updateCustomerInfo(customerId, customerRequestDto));
    }

}
