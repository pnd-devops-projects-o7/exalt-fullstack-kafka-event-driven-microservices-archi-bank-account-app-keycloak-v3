package com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.service;

import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.domain.output.CustomerOutputService;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.AddressEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.entity.CustomerEntity;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.repository.AddressRepo;
import com.exalt.exalthexagonalarchikafkakeycloakbsmscustomer.infra.output.repository.CustomerRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CustomerOutputServiceImpl implements CustomerOutputService {
    private final CustomerRepo customerRepo;
    private final AddressRepo addressRepo;

    public CustomerOutputServiceImpl(CustomerRepo customerRepo, AddressRepo addressRepo) {
        this.customerRepo = customerRepo;
        this.addressRepo = addressRepo;
    }

    @Override
    public void createCustomer(AddressEntity addressEntity, CustomerEntity customerEntity) {

        addressRepo.save(addressEntity);
        customerRepo.save(customerEntity);
    }

    @Override
    public List<CustomerEntity> loadAllPersistedCustomers() {
       return customerRepo.loadAllPersistedCustomers();
    }

    @Override
    public CustomerEntity findCustomerBy(String firstname, String lastname, String email) {
       return customerRepo.findCustomerBy(firstname,lastname,email);
    }

    @Override
    public CustomerEntity getCustomerById(UUID customerId) {
        return customerRepo.findCustomerById(customerId);
    }

    @Override
    public CustomerEntity archiveCustomer(CustomerEntity customerEntity) {
       return customerRepo.save(customerEntity);
    }

    @Override
    public void updateCustomerInfo(AddressEntity addressModel, CustomerEntity customerModel) {
        addressRepo.save(addressModel);
        customerRepo.save(customerModel);
    }
}
