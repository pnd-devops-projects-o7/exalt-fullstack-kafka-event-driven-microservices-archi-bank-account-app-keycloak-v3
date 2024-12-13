package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.output;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.CustomerEntity;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface CustomerOutputService {
    void createCustomer(AddressEntity addressEntity, CustomerEntity customerEntity);

    List<CustomerEntity> loadAllPersistedCustomers();
    CustomerEntity findCustomerBy(String firstname, String lastname, String email);

    CustomerEntity getCustomerById(UUID customerId);

    CustomerEntity archiveCustomer(CustomerEntity customerEntity);
    void updateCustomerInfo(AddressEntity addressEntity, CustomerEntity customerEntity);

    Map<String, Map<String, String>> getWelcome();
}
